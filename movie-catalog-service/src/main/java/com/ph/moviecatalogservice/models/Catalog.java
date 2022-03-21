package com.ph.moviecatalogservice.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Catalog {

    private String title;
    private int rating;

    public Catalog(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }
}
