package com.zjs.example.doubanz.net;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener<T> mListener;
    private Context mContext;

    public MyObserver(ObserverOnNextListener listener) {
        this.mListener = listener;
    }

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        mListener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
