package com.zjs.example.doubanz.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

public class MovieSubjects implements Parcelable, Comparable<MovieSubjects> {
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

    public MovieSubjects(Parcel in) {
        genres = in.createStringArrayList();
        title = in.readString();
        original_title = in.readString();
        collect_count = in.readInt();
        subtype = in.readString();
        year = in.readString();
        alt = in.readString();
        id = in.readString();
    }

    public static final Creator<MovieSubjects> CREATOR = new Creator<MovieSubjects>() {
        @Override
        public MovieSubjects createFromParcel(Parcel in) {
            return new MovieSubjects(in);
        }

        @Override
        public MovieSubjects[] newArray(int size) {
            return new MovieSubjects[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(genres);
        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeInt(collect_count);
        dest.writeString(subtype);
        dest.writeString(year);
        dest.writeString(alt);
        dest.writeString(id);
    }

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
        return "MovieSubjects{" +
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(@NonNull MovieSubjects o) {
        if ((this.getRating().getAverage() - o.getRating().getAverage()) > 0)
            return -1;
        else
            return 1;
    }
}
