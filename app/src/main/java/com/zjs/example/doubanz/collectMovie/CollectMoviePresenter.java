package com.zjs.example.doubanz.collectMovie;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zjs.example.doubanz.base.BaseApp;
import com.zjs.example.doubanz.bean.MovieData;
import com.zjs.example.doubanz.dataManager.MyDatabaseHelper;
import com.zjs.example.doubanz.dataManager.SqlConstant;

import java.util.List;

public class CollectMoviePresenter implements CollectMovieContract.Presenter {

    private static final String TAG = "CollectMoviePresenter";

    CollectMovieContract.View mView;
    MyDatabaseHelper mDatabaseHelper;

    public CollectMoviePresenter(CollectMovieContract.View view) {
        this.mView = view;
        view.setPresenter(this);
        mDatabaseHelper = new MyDatabaseHelper(BaseApp.getContext(), SqlConstant.DB_NAME, null, 1);
    }


    @Override
    public void loadData() {
        List<MovieData> datas = mDatabaseHelper.loadCollectedMovies();
        Log.d(TAG, "loadData: ffff" + datas.size());
        if (datas.size() == 0)
            mView.showEmpty();
        else
            mView.showData(datas);
    }

    @Override
    public void cancelCollection() {

    }

    @Override
    public void start() {
        loadData();
    }
}
