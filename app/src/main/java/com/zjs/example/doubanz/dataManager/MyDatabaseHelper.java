package com.zjs.example.doubanz.dataManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.bean.MovieData;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlConstant.CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<MovieData> loadCollectedMovies() {
        List<MovieData> movies = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(SqlConstant.MOVIE_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_ID));
                String image = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_IMAGE));
                String title = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_TITLE));
                String aka = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_AKA));
                String year = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_YEAR));
                String rating = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_RATING));
                String country = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_COUNTRY));
                String diretor = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_DIRECTOR));
                String genres = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_GENRES));
                String casts = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_CASTS));
                String summary = cursor.getString(cursor.getColumnIndex(SqlConstant.MOVIE_SUMMARY));

                MovieData movieData = new MovieData(id, image, title, aka, year, rating, country, diretor, genres, casts, summary);
                movies.add(movieData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movies;
    }

    /**
     * 收藏电影
     *
     * @param movie
     * @return
     */
    public boolean collectMovie(Movie movie) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SqlConstant.MOVIE_ID, movie.getId());
        values.put(SqlConstant.MOVIE_IMAGE, movie.getImages().getSmall());
        values.put(SqlConstant.MOVIE_TITLE, movie.getTitle());
        values.put(SqlConstant.MOVIE_AKA, movie.getAkaTotal());
        values.put(SqlConstant.MOVIE_YEAR, movie.getYear());
        values.put(SqlConstant.MOVIE_RATING, movie.getRatingString());
        values.put(SqlConstant.MOVIE_COUNTRY, movie.getCountryTotal());
        values.put(SqlConstant.MOVIE_DIRECTOR, movie.getDirectorTotal());
        values.put(SqlConstant.MOVIE_GENRES, movie.getGenresTotal());
        values.put(SqlConstant.MOVIE_CASTS, movie.getCastTotal());
        values.put(SqlConstant.MOVIE_SUMMARY, movie.getSummary());
        database.insert(SqlConstant.MOVIE_TABLE, null, values);
        database.close();
        return true;
    }

    /**
     * 删除收藏
     *
     * @param id 电影ID
     * @return 返回删除结果
     */
    public boolean cancleCollection(String id) {
        SQLiteDatabase database = getWritableDatabase();
        int deletedRow = database.delete(SqlConstant.MOVIE_TABLE, "id = ?", new String[]{id});
        if (deletedRow > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id 判断目标ID电影是否已经收藏
     * @return 如果未收藏，返回false
     */
    public boolean isMovieCollected(String id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(SqlConstant.MOVIE_TABLE, null, "id = ?", new String[]{id}, null, null, null);
        boolean isCollected = cursor.moveToFirst();
        cursor.close();
        return isCollected;
    }
}
