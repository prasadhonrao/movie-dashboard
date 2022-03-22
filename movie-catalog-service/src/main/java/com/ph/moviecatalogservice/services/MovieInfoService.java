package com.ph.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ph.moviecatalogservice.models.Movie;
import com.ph.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    public Movie getMovieInfo(Rating rating) {
        Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/api/movieinfo/" + rating.getMovieId(),
                Movie.class);
        return movie;
    }

    private Movie getFallbackMovieInfo(Rating rating) {
        return new Movie( "Fallback movie info" );
    }
}
