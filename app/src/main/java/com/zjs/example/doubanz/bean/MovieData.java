package com.zjs.example.doubanz.bean;

public class MovieData {
    private String id;
    private String image;
    private String title;
    private String aka;
    private String year;
    private String rating;
    private String country;
    private String director;
    private String genres;
    private String casts;
    private String summary;

    public MovieData(String id, String image, String title, String aka, String year, String rating, String country, String director, String genres, String casts, String summary) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.aka = aka;
        this.year = year;
        this.rating = rating;
        this.country = country;
        this.director = director;
        this.genres = genres;
        this.casts = casts;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
