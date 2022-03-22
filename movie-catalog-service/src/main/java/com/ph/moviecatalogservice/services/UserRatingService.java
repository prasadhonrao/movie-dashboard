package com.ph.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ph.moviecatalogservice.models.Rating;
import com.ph.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    public UserRating getUserRatings(String userId) {
        return restTemplate.getForObject(
                "http://MOVIE-RATING-SERVICE/api/rating/users/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRatings(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRating(Arrays.asList(new Rating( "0",0 )));
        return userRating;
    }
}
