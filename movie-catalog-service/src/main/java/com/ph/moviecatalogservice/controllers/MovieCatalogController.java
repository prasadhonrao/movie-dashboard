package com.ph.moviecatalogservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ph.moviecatalogservice.models.Catalog;
import com.ph.moviecatalogservice.models.Movie;
import com.ph.moviecatalogservice.models.Rating;
import com.ph.moviecatalogservice.models.UserRating;
import com.ph.moviecatalogservice.services.MovieInfoService;
import com.ph.moviecatalogservice.services.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moviecatalog")
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    UserRatingService userRatingService;

    @GetMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable String userId) {
        UserRating ratings = userRatingService.getUserRatings(userId);
        System.out.println(ratings);

        List<Catalog> catalogList = ratings.getUserRating().stream().map(rating -> {
            Movie movie = movieInfoService.getMovieInfo(rating);
            return new Catalog(movie.getTitle(), rating.getRating());
        }).collect(Collectors.toList());

        System.out.println(catalogList);

        return catalogList;
    }

}
