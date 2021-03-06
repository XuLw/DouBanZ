package com.zjs.example.doubanz.detail;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.Top250.Top250MoviesFragment;
import com.zjs.example.doubanz.base.BaseActivity;
import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.net.ApiMethods;
import com.zjs.example.doubanz.net.MyObserver;
import com.zjs.example.doubanz.net.ObserverOnNextListener;

import butterknife.BindView;

public class CollectedMovieDetailActivity extends BaseActivity {

    private static final String TAG = "MovieDetailActivity";

    @BindView(R.id.t_movie_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ctl_movie_detail_collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.iv_movie_detail_poster)
    ImageView mMoviePosterIv;
    @BindView(R.id.tv_movie_detail_title)
    TextView mMovieTitleTv;
    @BindView(R.id.tv_movie_detail_country)
    TextView mMovieCountryTv;
    @BindView(R.id.tv_movie_detail_year)
    TextView mMovieYearTv;
    @BindView(R.id.tv_movie_detail_casts)
    TextView mMovieCastsTv;
    @BindView(R.id.tv_movie_detail_aka)
    TextView mMovieAkaTv;
    @BindView(R.id.tv_movie_detail_summary)
    TextView mMovieSummaryTv;
    @BindView(R.id.tv_movie_detail_genres)
    TextView mMovieGenreTv;
    @BindView(R.id.tv_movie_detail_director)
    TextView mMovieDirector;
    @BindView(R.id.tv_movie_detail_rating)
    TextView mMovieRating;

    private Movie mMovie;
    private String id;
    private String moviePosterUrl;

    @Override
    protected View initContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_movie_detail, null);
        return view;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        Intent intent = getIntent();
        this.id = intent.getStringExtra(Top250MoviesFragment.INTENT_KEY_MOVIE_ID);
        this.moviePosterUrl = intent.getStringExtra(Top250MoviesFragment.INTENT_KEY_MOVIE_POSTER);
        getMovie();
        return true;
    }

    @Override
    protected void initOptions() {
        Glide.with(this).load(moviePosterUrl).into(mMoviePosterIv);
    }

    private void getMovie() {
        MyObserver<Movie> getMovieObserver = new MyObserver<Movie>(this, new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                CollectedMovieDetailActivity.this.mMovie = movie;
                Log.d(TAG, "onNext: " + movie.toString());
                initView();
            }
        });

        ApiMethods.getMovieById(getMovieObserver, this.id);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);
        mCollapsingToolbar.setTitle(mMovie.getTitle());
        mMovieTitleTv.setText(mMovie.getTitle());
        mMovieYearTv.setText("年 份 :  " + mMovie.getYear());
        mMovieSummaryTv.setText("  " + mMovie.getSummary());
        mMovieAkaTv.setText("别 名 :  " + mMovie.getAkaTotal());
        mMovieCastsTv.setText(mMovie.getCastTotal());
        mMovieGenreTv.setText("类 别 :  " + mMovie.getGenresTotal());
        mMovieDirector.setText("导 演 :  " + mMovie.getDirectorTotal());
        mMovieCountryTv.setText("国 家 :  " + mMovie.getCountries());
        mMovieRating.setText("评 分 :  " + mMovie.getRatingString());

    }
}
