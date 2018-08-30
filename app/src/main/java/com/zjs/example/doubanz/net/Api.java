package com.zjs.example.doubanz.net;

import com.zjs.example.doubanz.bean.Book;
import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.bean.SearchBook;
import com.zjs.example.doubanz.bean.SearchMovie;
import com.zjs.example.doubanz.bean.Top250Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    /**
     * 调用豆瓣API  根据isbn获取图书信息
     *
     * @param isbn
     * @return
     */
    @GET(":{isbn}")
    Observable<Book> getBookInfoByIsbn(@Path("isbn") String isbn);

    // 根据关键词搜索图书
    @GET("search")
    Observable<SearchBook> searchBookByKeyword(@Query("q") String keyword,
                                               @Query("start") int start,
                                               @Query("count") int count);


    // 获取指定起始位置的Top250电影
    @GET("top250")
    Observable<Top250Movie> getTop250Movies(@Query("start") int start,
                                            @Query("count") int count);

    // 根据关键词搜索电影
    @GET("search")
    Observable<SearchMovie> searchMovieByKeyword(@Query("q") String keyword,
                                                 @Query("start") int start,
                                                 @Query("count") int count);

    // 根据标签搜索电影
    @GET("search")
    Observable<SearchMovie> searchMovieByTag(@Query("tag") String tag,
                                             @Query("start") int start,
                                             @Query("count") int count);

    // 根据电影的id获取具体的电影信息
    @GET("subject/{id}")
    Observable<Movie> getMovieById(@Path("id") String id);

}