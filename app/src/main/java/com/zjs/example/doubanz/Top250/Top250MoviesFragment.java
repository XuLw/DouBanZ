package com.zjs.example.doubanz.Top250;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.adapter.RecyclerAdapter;
import com.zjs.example.doubanz.base.BaseApp;
import com.zjs.example.doubanz.base.BaseFragment;
import com.zjs.example.doubanz.bean.MovieSubjects;
import com.zjs.example.doubanz.detail.MovieDetailActivity;
import com.zjs.example.doubanz.search.SearchActivity;
import com.zjs.example.doubanz.search.SearchTypeEnum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Top250MoviesFragment extends BaseFragment implements Top250MovieContract.View, NestedScrollView.OnScrollChangeListener, View.OnClickListener {

    private static final String TAG = "Top250MoviesFragment";
    public static final String INTENT_KEY_MOVIE_ID = "movieId";
    public static final String INTENT_KEY_MOVIE_POSTER = "moviePoster";

    @BindView(R.id.rv_top250Movie)
    RecyclerView mRecycler;
    @BindView(R.id.tv_recycler_empty)
    View mEmptyView;
    @BindView(R.id.nsv_top250Movie)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.tv_loading_more)
    TextView mLoadMoreTv;
    @BindView(R.id.tv_no_more)
    TextView mNoMoreTv;
    @BindView(R.id.rl_fab_root)
    RelativeLayout fabRoot;
    @BindView(R.id.fab_movie_search_container)
    FloatingActionButton movieSearchContainerFab;
    @BindView(R.id.ll_book_search_root)
    LinearLayout bookSearchRoot;
    @BindView(R.id.ll_movie_type_search_root)
    LinearLayout movieTypeSearchRoot;
    @BindView(R.id.ll_movie_search_root)
    LinearLayout movieSearchRoot;
    @BindView(R.id.fab_movie_search)
    FloatingActionButton movieFab;
    @BindView(R.id.fab_movie_type_search)
    FloatingActionButton movieTypeFab;
    @BindView(R.id.fab_book_search)
    FloatingActionButton bookFab;


    RecyclerAdapter<MovieSubjects> mRecyclerAdapter;
    private Top250MovieContract.Presenter mPresenter;
    private boolean isPressed;
    private AnimatorSet searchBookTranslate;
    private AnimatorSet searchMovieTranslate;
    private AnimatorSet searchMovieTypeTranslate;


    public Top250MoviesFragment() {

        mRecyclerAdapter = new RecyclerAdapter<MovieSubjects>() {
            @Override
            protected int getItemViewType(int position, MovieSubjects movie) {
                return R.layout.item_recycler_top250;
            }

            @Override
            protected ViewHolder onCreateViewHolder(View root, int viewType) {
                return new Top250MoviesFragment.ViewHolder(root);
            }
        };
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_top250;
    }

    @Override
    protected void initData() {
        mPresenter.start();

        isPressed = false;
        searchBookTranslate = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.search_anim);
        searchMovieTranslate = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.search_anim);
        searchMovieTypeTranslate = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.search_anim);
        Log.d(TAG, "initData: ");
    }

    @Override
    protected void initWidget(View view) {

        Log.d(TAG, "initWidget: ");
        mNestedScrollView.setOnScrollChangeListener(this);
        mRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecycler.setAdapter(mRecyclerAdapter);
        mRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoadMoreView() {
        if (mLoadMoreTv.getVisibility() == View.GONE) {
            mLoadMoreTv.setVisibility(View.VISIBLE);
        }
        if (mNoMoreTv.getVisibility() == View.VISIBLE) {
            mNoMoreTv.setVisibility(View.GONE);
        }
        if (mEmptyView.getVisibility() == View.VISIBLE) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoadMore() {
        if (mLoadMoreTv.getVisibility() == View.VISIBLE) {
            mLoadMoreTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoMoreView() {
        if (mNoMoreTv.getVisibility() == View.GONE) {
            mNoMoreTv.setVisibility(View.VISIBLE);
        }
        if (mLoadMoreTv.getVisibility() == View.VISIBLE) {
            mLoadMoreTv.setVisibility(View.GONE);
        }
        if (mEmptyView.getVisibility() == View.VISIBLE) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMoreData(List<MovieSubjects> list) {
        mRecyclerAdapter.add(list);
    }

    @Override
    public void setPresenter(Top250MovieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if ((scrollY >= (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight()
                - v.getMeasuredHeight()) && scrollY > oldScrollY) {
            if (mRecyclerAdapter.getItemCount() > 0) {
                if (mPresenter.canLoadMore()) {
                    showLoadMoreView();
                    mPresenter.loadMore();
                } else {
                    showNoMoreView();
                }
            }
        }
    }

    @OnClick({R.id.fab_movie_search, R.id.fab_book_search, R.id.fab_movie_type_search, R.id.fab_movie_search_container, R.id.rl_fab_root})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_movie_search_container:
                isPressed = !isPressed;
                fabRoot.setVisibility(isPressed ? View.VISIBLE : View.GONE);
                if (isPressed) {
                    searchMovieTranslate.setTarget(movieSearchRoot);
                    searchMovieTranslate.start();
                    searchBookTranslate.setTarget(bookSearchRoot);
                    searchBookTranslate.setStartDelay(100);
                    searchBookTranslate.start();
                    searchMovieTypeTranslate.setTarget(movieTypeSearchRoot);
                    searchMovieTypeTranslate.setStartDelay(200);
                    searchMovieTypeTranslate.start();
                }
                break;
            case R.id.fab_book_search:
                hideMenu();
                startActivity(SearchActivity.newIntent(mContext, SearchTypeEnum.BOOK));
                break;
            case R.id.fab_movie_search:
                hideMenu();
                startActivity(SearchActivity.newIntent(mContext, SearchTypeEnum.MOVIE));
                break;
            case R.id.fab_movie_type_search:
                hideMenu();
                startActivity(SearchActivity.newIntent(mContext, SearchTypeEnum.MOVIE_TYPE));
                break;
            case R.id.rl_fab_root:
                hideMenu();
                break;
        }
    }

    private void hideMenu() {
        fabRoot.setVisibility(View.GONE);
        isPressed = false;
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<MovieSubjects> {

        @BindView(R.id.rl_movie_item_root)
        RelativeLayout mLayout;
        @BindView(R.id.iv_movie_poster)
        ImageView moviePoster;
        @BindView(R.id.tv_movie_title)
        TextView movieTitle;
        @BindView(R.id.tv_movie_star)
        TextView movieStar;
        @BindView(R.id.iv_star_1)
        ImageView star1;
        @BindView(R.id.iv_star_2)
        ImageView star2;
        @BindView(R.id.iv_star_3)
        ImageView star3;
        @BindView(R.id.iv_star_4)
        ImageView star4;
        @BindView(R.id.iv_star_5)
        ImageView star5;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(final MovieSubjects movie) {
            Glide.with(BaseApp.getContext())
                    .load(movie.getImages().getSmall())
                    .into(moviePoster);
            movieTitle.setText(movie.getTitle());
            movieStar.setText("" + movie.getRating().getAverage());
            setStar(movie.getRating().getAverage());
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra(INTENT_KEY_MOVIE_ID, movie.getId());
                    intent.putExtra(INTENT_KEY_MOVIE_POSTER, movie.getImages().getSmall());
                    startActivity(intent);
                }
            });
        }

        private void setStar(double point) {
            int full = ((int) point) / 2;
            List<ImageView> imageViews = new ArrayList<>();

            imageViews.add(star1);
            imageViews.add(star2);
            imageViews.add(star3);
            imageViews.add(star4);
            imageViews.add(star5);

            // 填整颗星星
            int indexOfStar = 0;
            for (; indexOfStar < full; indexOfStar++) {
                Glide.with(BaseApp.getContext()).load(R.drawable.star_full).into(imageViews.get(indexOfStar));
            }

            // 半颗星
            double half = point - (full * 2);
            if (half - 0 > 0)
                Glide.with(BaseApp.getContext()).load(R.drawable.star_half).into(imageViews.get(indexOfStar++));

            // 空星星
            for (; indexOfStar < 5; indexOfStar++) {
                Glide.with(BaseApp.getContext()).load(R.drawable.star_empty).into(imageViews.get(indexOfStar));
            }

        }
    }

}
