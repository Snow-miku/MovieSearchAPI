package com.snowmiku.moviesearchapi.pojo;

public class MovieCountDTO {
    private int count;
    private int year;

    // Constructor
    public MovieCountDTO(int count, int year) {
        this.count = count;
        this.year = year;
    }

    // Getters and Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}