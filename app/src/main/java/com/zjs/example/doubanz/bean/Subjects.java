package com.zjs.example.doubanz.bean;

import java.util.List;

public class Subjects {
    private Rating rating;
    private List<String> genres;
    private String title;
    private String original_title;
    private List<Casts> casts;
    private int collect_count;
    private String subtype;
    private List<Directors> directors;
    private String year;
    private Images images;
    private String alt;
    private String id;

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Casts> getCasts() {
        return this.casts;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public int getCollect_count() {
        return this.collect_count;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public List<Directors> getDirectors() {
        return this.directors;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return this.year;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Images getImages() {
        return this.images;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }


    @Override
    public String toString() {
        return "Subjects{" +
                "rating=" + rating +
                ", genres=" + genres +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", casts=" + casts +
                ", collect_count=" + collect_count +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
