package com.zjs.example.doubanz.bean;

import java.util.Arrays;
import java.util.List;

public class Movie {

    private Rating rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private Images images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private String do_count;
    private String share_url;
    private String seasons_count;
    private String schedule_url;
    private String episodes_count;
    private List<String> countries;
    private List<String> genres;
    private int collect_count;
    private List<Casts> casts;
    private String current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private List<Directors> directors;
    private int comments_count;
    private int ratings_count;
    private List<String> aka;

    public String getAkaTotal() {
        StringBuilder akaTotal = new StringBuilder();
        int indexOfAka = 0;
        for (; indexOfAka < aka.size() - 1; indexOfAka++) {
            akaTotal.append(aka.get(indexOfAka)).append(" / ");
        }
        if (aka.size() != 0)
            akaTotal.append(aka.get(indexOfAka));
        else
            akaTotal.append("unknown");

        return akaTotal.toString();
    }

    public String getDirectorTotal() {
        StringBuilder diretorsTotal = new StringBuilder();
        int indexOfDiretor = 0;
        for (; indexOfDiretor < directors.size() - 1; indexOfDiretor++) {
            diretorsTotal.append(directors.get(indexOfDiretor).getName()).append(" / ");
        }
        if (directors.size() != 0)
            diretorsTotal.append(directors.get(indexOfDiretor).getName());
        else
            diretorsTotal.append("unknown");
        return diretorsTotal.toString();
    }

    public String getGenresTotal() {
        StringBuilder genreTotal = new StringBuilder();
        int indexOfGenre = 0;
        for (; indexOfGenre < genres.size() - 1; indexOfGenre++) {
            genreTotal.append(genres.get(indexOfGenre)).append(" / ");
        }
        if (genres.size() != 0)
            genreTotal.append(genres.get(indexOfGenre));
        else
            genreTotal.append("unknown");
        return genreTotal.toString();
    }

    public String getCountryTotal() {
        StringBuilder countrytTotal = new StringBuilder();
        int indexOfCountry = 0;
        for (; indexOfCountry < countries.size() - 1; indexOfCountry++) {
            countrytTotal.append(countries.get(indexOfCountry)).append(" / ");
        }
        if (countries.size() != 0)
            countrytTotal.append(countries.get(indexOfCountry));
        else
            countrytTotal.append("unknown");
        return countrytTotal.toString();
    }

    public String getCastTotal() {
        StringBuilder castTotal = new StringBuilder();
        int indexOfCast = 0;
        for (; indexOfCast < casts.size() - 1; indexOfCast++) {
            castTotal.append(casts.get(indexOfCast).getName()).append(" / ");
        }
        if (casts.size() != 0)
            castTotal.append(casts.get(indexOfCast).getName());
        else
            castTotal.append("unknown");
        return castTotal.toString();
    }

    public String getRatingString() {
        return rating.getAverage() + " (" + ratings_count + ")";
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getReviews_count() {
        return this.reviews_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public int getWish_count() {
        return this.wish_count;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getDouban_site() {
        return this.douban_site;
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

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getMobile_url() {
        return this.mobile_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDo_count(String do_count) {
        this.do_count = do_count;
    }

    public String getDo_count() {
        return this.do_count;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_url() {
        return this.share_url;
    }

    public void setSeasons_count(String seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSeasons_count() {
        return this.seasons_count;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getSchedule_url() {
        return this.schedule_url;
    }

    public void setEpisodes_count(String episodes_count) {
        this.episodes_count = episodes_count;
    }

    public String getEpisodes_count() {
        return this.episodes_count;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getCountries() {
        return this.countries;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public int getCollect_count() {
        return this.collect_count;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Casts> getCasts() {
        return this.casts;
    }

    public void setCurrent_season(String current_season) {
        this.current_season = current_season;
    }

    public String getCurrent_season() {
        return this.current_season;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return this.summary;
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

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getComments_count() {
        return this.comments_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getRatings_count() {
        return this.ratings_count;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public List<String> getAka() {
        return this.aka;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rating=" + rating +
                ", reviews_count=" + reviews_count +
                ", wish_count=" + wish_count +
                ", douban_site='" + douban_site + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", title='" + title + '\'' +
                ", do_count='" + do_count + '\'' +
                ", share_url='" + share_url + '\'' +
                ", seasons_count='" + seasons_count + '\'' +
                ", schedule_url='" + schedule_url + '\'' +
                ", episodes_count='" + episodes_count + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                ", collect_count=" + collect_count +
                ", casts=" + casts +
                ", current_season='" + current_season + '\'' +
                ", original_title='" + original_title + '\'' +
                ", summary='" + summary + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", comments_count=" + comments_count +
                ", ratings_count=" + ratings_count +
                ", aka=" + aka +
                '}';
    }
}
