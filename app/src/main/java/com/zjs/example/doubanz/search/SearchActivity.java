package com.zjs.example.doubanz.search;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.base.BaseActivity;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {

    private static final String INTENT_KEY_SEARCH_TYPE = "searchType";
    private static final String TAG = "SearchActivity";

    @BindView(R.id.content_frame)
    FrameLayout mFrameLayout;


    public static Intent newIntent(Context context, SearchTypeEnum type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(INTENT_KEY_SEARCH_TYPE, type);
        return intent;
    }

    @Override
    protected void initOptions() {
        SearchTypeEnum type = (SearchTypeEnum) getIntent().getSerializableExtra(INTENT_KEY_SEARCH_TYPE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SearchFragment fragment = new SearchFragment();
        fragment.setType(type);
        setActionBarTitle(type);
        new SearchPresenter(fragment);
        transaction.add(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    protected View initContentView() {
        return getLayoutInflater().inflate(R.layout.activity_search, null);
    }

    private void setActionBarTitle(SearchTypeEnum type) {
        if (type == SearchTypeEnum.MOVIE) {
            getSupportActionBar().setTitle("电影搜索");
        } else if (type == SearchTypeEnum.MOVIE_TYPE) {
            getSupportActionBar().setTitle("电影标签搜索");
        } else {
            getSupportActionBar().setTitle("图书搜索");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
