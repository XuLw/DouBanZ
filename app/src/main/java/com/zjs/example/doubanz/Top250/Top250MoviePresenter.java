package com.zjs.example.doubanz.Top250;

import android.util.Log;

import com.zjs.example.doubanz.bean.Subjects;
import com.zjs.example.doubanz.bean.Top250Movie;
import com.zjs.example.doubanz.net.ApiMethods;
import com.zjs.example.doubanz.net.MyObserver;
import com.zjs.example.doubanz.net.ObserverOnNextListener;

import java.util.List;

public class Top250MoviePresenter implements Top250MovieContract.Presenter {

    private static final String TAG = "Top250MoviePresenter";
    private static final int REQUEST_COUNT = 20;

    private int requestStartPosition;
    private Top250MovieContract.View mView;
//    private List<Subjects> mMovies;


    public Top250MoviePresenter(Top250MovieContract.View view) {
        mView = view;
        mView.setPresenter(this);
        requestStartPosition = 0;
    }

    @Override
    public boolean canLoadMore() {
        return false;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void start() {
        func();
        Log.d(TAG, "start: ");
    }

    private void func() {
        MyObserver<Top250Movie> top250MovieMyObserver = new MyObserver<>(new ObserverOnNextListener<Top250Movie>() {
            @Override
            public void onNext(Top250Movie o) {

                requestStartPosition += REQUEST_COUNT;
//                mMovies.addAll(o.getSubjects());
                mView.showMoreData(o.getSubjects());

                for (Subjects subject : o.getSubjects()) {
                    Log.d(TAG, "onNext: " + subject.toString());
                }
            }
        });

        ApiMethods.getTopMovie(top250MovieMyObserver, requestStartPosition, REQUEST_COUNT);
    }
}
