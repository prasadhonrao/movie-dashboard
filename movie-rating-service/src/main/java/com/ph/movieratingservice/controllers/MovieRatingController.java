package com.ph.movieratingservice.controllers;

import com.ph.movieratingservice.models.Rating;
import com.ph.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class MovieRatingController {

    @GetMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId) {
        return new Rating(movieId, 5);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRating(List.of(
                new Rating("titanic", 5),
                new Rating("toystory1", 4)
        ));
        return userRating;
    }

}
