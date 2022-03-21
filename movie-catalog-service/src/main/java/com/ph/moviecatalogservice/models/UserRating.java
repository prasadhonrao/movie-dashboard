package com.ph.moviecatalogservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class UserRating {
    private List<Rating> userRating;
}
