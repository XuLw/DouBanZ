package com.zjs.example.doubanz.Top250;

import android.util.Log;

import com.zjs.example.doubanz.bean.Top250Movie;
import com.zjs.example.doubanz.net.ApiMethods;
import com.zjs.example.doubanz.net.MyObserver;
import com.zjs.example.doubanz.net.ObserverOnNextListener;

public class Top250MoviePresenter implements Top250MovieContract.Presenter {

    private static final String TAG = "Top250MoviePresenter";
    private static final int TOTAL_COUNT = 250;
    private static final int REQUEST_COUNT = 20;

    private int requestStartPosition;
    private Top250MovieContract.View mView;
//    private List<MovieSubjects> mMovies;


    public Top250MoviePresenter(Top250MovieContract.View view) {
        mView = view;
        mView.setPresenter(this);
        requestStartPosition = 0;
    }

    @Override
    public boolean canLoadMore() {
        return requestStartPosition < 250;
    }

    @Override
    public void loadMore() {
        loadData();
    }

    @Override
    public void start() {
        loadData();
        Log.d(TAG, "start: ");
    }

    private void loadData() {
        MyObserver<Top250Movie> top250MovieMyObserver = new MyObserver<>(new ObserverOnNextListener<Top250Movie>() {
            @Override
            public void onNext(Top250Movie o) {

                requestStartPosition += REQUEST_COUNT;
                mView.showMoreData(o.getSubjects());
                mView.hideLoadMore();
//                for (MovieSubjects subject : o.getSubjects()) {
//                    Log.d(TAG, "onNext: " + subject.toString());
//                }
            }
        });

        ApiMethods.getTopMovie(top250MovieMyObserver, requestStartPosition, REQUEST_COUNT);
    }

}
