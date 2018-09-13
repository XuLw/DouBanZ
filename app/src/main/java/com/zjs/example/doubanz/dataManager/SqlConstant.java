package com.zjs.example.doubanz.dataManager;

public class SqlConstant {

    public static final String CREATE_MOVIE = "create table movie (" +
            "id text primary key," +
            "image text," +
            "title text," +
            "aka text," +
            "year text," +
            "rating text," +
            "countries text," +
            "diretors text," +
            "genres text," +
            "casts text," +
            "summary text)";

    public static final String DB_NAME = "movie_db.db";
    public static final String MOVIE_TABLE = "movie";
    public static final String MOVIE_ID = "id";
    public static final String MOVIE_IMAGE = "image";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_AKA = "aka";
    public static final String MOVIE_YEAR = "year";
    public static final String MOVIE_RATING = "rating";
    public static final String MOVIE_COUNTRY = "countries";
    public static final String MOVIE_DIRECTOR = "diretors";
    public static final String MOVIE_GENRES = "genres";
    public static final String MOVIE_CASTS = "casts";
    public static final String MOVIE_SUMMARY = "summary";

}
