package com.snowmiku.moviesearchapi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Movie {
    @JsonProperty("page")
    private int page;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total")
    private int total;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("data")
    private List<MovieData> data;

    public Collection<Object> getData() {
        return Collections.singleton(data);
    }

    public int getTotalPages() {
        return totalPages;
    }

    // Getters and Setters
}

// MovieData class representing each movie
class MovieData {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdbID;

    // Getters and Setters
}
