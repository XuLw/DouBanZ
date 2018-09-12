package com.zjs.example.doubanz.search;

import android.util.Log;

import com.zjs.example.doubanz.bean.MovieSubjects;
import com.zjs.example.doubanz.bean.SearchBook;
import com.zjs.example.doubanz.bean.SearchMovie;
import com.zjs.example.doubanz.net.ApiMethods;
import com.zjs.example.doubanz.net.MyObserver;
import com.zjs.example.doubanz.net.ObserverOnNextListener;

import java.util.Collections;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";

    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void searchBook(String text) {

        mView.showLoading();

        MyObserver<SearchBook> searchBookMyObserver = new MyObserver<SearchBook>(new ObserverOnNextListener<SearchBook>() {
            @Override
            public void onNext(SearchBook searchBook) {
                if (searchBook.getCount() != 0) {
                    mView.showData(searchBook.getBooks());
                    mView.hideLoading();
                } else {
                    mView.hideLoading();
                    mView.showEmpty();
                }
            }
        });

        ApiMethods.searchBook(searchBookMyObserver, text, 0, 20);
    }

    @Override
    public void searchMovie(String text) {

        mView.showLoading();

        MyObserver<SearchMovie> searchMovieMyObserver = new MyObserver<>(new ObserverOnNextListener<SearchMovie>() {
            @Override
            public void onNext(SearchMovie movie) {
                if (movie.getTotal() != 0) {
                    List<MovieSubjects> temp = movie.getSubjects();
                    // 进行排序
                    Collections.sort(temp);
                    mView.showData(temp);
                    mView.hideLoading();
                } else {
                    mView.hideLoading();
                    mView.showEmpty();
                }
            }
        });

        ApiMethods.searchMovie(searchMovieMyObserver, SearchTypeEnum.MOVIE, text, 0, 20);
    }

    @Override
    public void searchMovieType(String text) {
        mView.showLoading();

        MyObserver<SearchMovie> searchMovieMyObserver = new MyObserver<>(new ObserverOnNextListener<SearchMovie>() {
            @Override
            public void onNext(SearchMovie movie) {
                if (movie.getTotal() != 0) {
                    List<MovieSubjects> temp = movie.getSubjects();
                    // 进行排序
                    Collections.sort(temp);
                    mView.showData(temp);
                    mView.hideLoading();
                } else {
                    mView.hideLoading();
                    mView.showEmpty();
                }
            }
        });

        ApiMethods.searchMovie(searchMovieMyObserver, SearchTypeEnum.MOVIE_TYPE, text, 0, 20);
    }

    @Override
    public void start() {

    }
}
