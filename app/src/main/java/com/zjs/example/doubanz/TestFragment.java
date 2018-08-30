package com.zjs.example.doubanz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjs.example.doubanz.base.BaseFragment;

import junit.framework.Test;

import butterknife.BindView;

public class TestFragment extends BaseFragment {

    @BindView(R.id.tv_content)
    TextView mTextView;

    String title;

    public TestFragment() {
        super();
    }

    public static TestFragment newInstance(String text){
        TestFragment fragment = new TestFragment();
        fragment.setText(text);
        return fragment;
    }

    public void setText(String text) {
        title = text;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.test_fratment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initWidget(View view) {
        mTextView.setText(title);
    }
}
