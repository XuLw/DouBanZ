package com.zjs.example.doubanz.bean;

import java.util.Arrays;

public class Movie {
    private String title;
    private String original_title;
    private Rating rating;
    private int ratings_count;
    private int wish_count;
    private int collect_count;
    private String year;
    private String[] languages;
    private String[] durations;
    private String summary;

    public Movie() {
        this.title = "";
        this.original_title = "";
        this.ratings_count = 0;
        this.wish_count = 0;
        this.collect_count = 0;
        this.year = "";
        this.summary = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getDurations() {
        return durations;
    }

    public void setDurations(String[] durations) {
        this.durations = durations;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private class Rating {
        int min;
        int max;
        float average;

        @Override
        public String toString() {
            return "Rating{" +
                    "min=" + min +
                    ", max=" + max +
                    ", average=" + average +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", rating=" + rating +
                ", ratings_count=" + ratings_count +
                ", wish_count=" + wish_count +
                ", collect_count=" + collect_count +
                ", year='" + year + '\'' +
                ", languages=" + Arrays.toString(languages) +
                ", durations=" + Arrays.toString(durations) +
                ", summary='" + summary + '\'' +
                '}';
    }
}
