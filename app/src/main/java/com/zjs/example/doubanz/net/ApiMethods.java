package com.zjs.example.doubanz.net;

import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.bean.SearchBook;
import com.zjs.example.doubanz.bean.SearchMovie;
import com.zjs.example.doubanz.bean.Top250Movie;
import com.zjs.example.doubanz.common.Constant;
import com.zjs.example.doubanz.search.SearchTypeEnum;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {

    private static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    // 写网络请求方法
    public static void getTopMovie(Observer<Top250Movie> observer, int start, int count) {
        ApiSubscribe(ApiService.getInstance().getApi(Constant.DOUBAN_MOVIE).getTop250Movies(start, count), observer);
    }

    public static void searchMovie(Observer<SearchMovie> observer, SearchTypeEnum type, String search, int start, int count) {
        if (SearchTypeEnum.MOVIE_TYPE == type)
            ApiSubscribe(ApiService.getInstance().getApi(Constant.DOUBAN_MOVIE).searchMovieByTag(search, start, count), observer);

        if (SearchTypeEnum.MOVIE == type)
            ApiSubscribe(ApiService.getInstance().getApi(Constant.DOUBAN_MOVIE).searchMovieByKeyword(search, start, count), observer);
    }

    public static void getMovieById(Observer<Movie> observer, String id) {
        ApiSubscribe(ApiService.getInstance().getApi(Constant.DOUBAN_MOVIE).getMovieById(id), observer);
    }

    public static void searchBook(Observer<SearchBook> observer, String search, int start, int count) {
        ApiSubscribe(ApiService.getInstance().getApi(Constant.DOUBAN_BOOK).searchBookByKeyword(search, start, count), observer);
    }
}
