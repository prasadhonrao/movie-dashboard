package com.ph.movieinfoservice.controllers;

import com.ph.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movieinfo")
public class MovieInfoController {

    List<Movie> movies = new ArrayList<>();

    @GetMapping("/")
    public List<Movie> getMovies() {
        movies.add(new Movie("titanic", "The Titanic"));
        movies.add(new Movie("toystory1", "Toy Story Part 1"));
        movies.add(new Movie("toystory2", "Toy Story Part 2"));
        return movies;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable String id) {
        System.out.println(id);
        Movie movie = movies.stream().filter(m -> m.getId().equals(id)).findFirst().get();
        return movie;
    }
}
