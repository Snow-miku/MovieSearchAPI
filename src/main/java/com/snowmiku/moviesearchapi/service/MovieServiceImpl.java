package com.snowmiku.moviesearchapi.service;

import com.snowmiku.moviesearchapi.pojo.Movie;
import com.snowmiku.moviesearchapi.pojo.MovieCountDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MovieServiceImpl implements MovieService {

    private final RestTemplate restTemplate;

    public MovieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MovieCountDTO getMovieCountByYear(int year) {
        AtomicInteger totalCount = new AtomicInteger(0);
        int page = 1;
        Movie response;

        try {
            do {
                String url = UriComponentsBuilder.fromHttpUrl("https://jsonmock.hackerrank.com/api/movies")
                        .queryParam("Year", year)
                        .queryParam("page", page)
                        .toUriString();

                // Make the API request
                response = restTemplate.getForObject(url, Movie.class);

                // If the response is valid, accumulate the count
                if (response != null && response.getData() != null) {
                    totalCount.addAndGet(response.getData().size());
                }

                // Increment the page counter
                page++;
            } while (response != null && page <= response.getTotalPages());
        } catch (HttpClientErrorException e) {
            // Handle client-side HTTP errors (4xx)
            System.err.println("Client error while fetching movies: " + e.getMessage());
            throw new RestClientException("Failed to fetch movies due to client error", e);
        } catch (HttpServerErrorException e) {
            // Handle server-side HTTP errors (5xx)
            System.err.println("Server error while fetching movies: " + e.getMessage());
            throw new RestClientException("Failed to fetch movies due to server error", e);
        } catch (RestClientException e) {
            // Handle other REST client exceptions
            System.err.println("Error occurred while fetching movies: " + e.getMessage());
            throw e;  // Re-throw the exception to let it propagate
        } catch (Exception e) {
            // Handle any other exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RestClientException("An unexpected error occurred", e);
        }

        return new MovieCountDTO(totalCount.get(), year);
    }
}