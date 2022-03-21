package com.ph.moviecatalogservice.controllers;

import com.ph.moviecatalogservice.models.Catalog;
import com.ph.moviecatalogservice.models.Movie;
import com.ph.moviecatalogservice.models.Rating;
import com.ph.moviecatalogservice.models.UserRating;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moviecatalog")
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable String userId) {
        UserRating ratings = restTemplate.getForObject(
                        "http://MOVIE-RATING-SERVICE/api/rating/users/" + userId, UserRating.class);
        System.out.println(ratings);

        List<Catalog> catalogList = ratings.getUserRating().stream().map(rating -> {
            System.out.println("Movie Id: " + rating.getMovieId());
            Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/api/movieinfo/" + rating.getMovieId(),
                    Movie.class);
            System.out.println(movie);
            return new Catalog(movie.getTitle(), rating.getRating());
        }).collect(Collectors.toList());

        System.out.println(catalogList);

        return catalogList;
    }
}
