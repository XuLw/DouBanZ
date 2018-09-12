package com.zjs.example.doubanz.collectMovie;

import com.zjs.example.doubanz.base.BasePresenter;
import com.zjs.example.doubanz.base.BaseView;
import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.bean.MovieData;

import java.util.List;

public interface CollectMovieContract {

    interface View extends BaseView<Presenter> {

        void showData(List<MovieData> movies);

        void showEmpty();

        void hideEmpty();
    }

    interface Presenter extends BasePresenter {

        void loadData();

        void cancelCollection();

    }
}
