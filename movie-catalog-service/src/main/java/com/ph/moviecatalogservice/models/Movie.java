package com.ph.moviecatalogservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    private String title;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }
}
