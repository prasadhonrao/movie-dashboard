package com.ph.movieinfoservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    private String id;
    private String title;

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
