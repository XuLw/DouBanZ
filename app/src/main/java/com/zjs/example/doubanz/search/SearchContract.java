package com.zjs.example.doubanz.search;

import com.zjs.example.doubanz.base.BasePresenter;
import com.zjs.example.doubanz.base.BaseView;

import java.util.List;

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void showData(List subjects);

        void showEmpty();
    }

    interface Presenter extends BasePresenter {

        void searchBook(String text);

        void searchMovie(String text);

        void searchMovieType(String text);

    }
}
