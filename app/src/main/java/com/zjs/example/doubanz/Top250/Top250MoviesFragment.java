package com.zjs.example.doubanz.Top250;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.adapter.RecyclerAdapter;
import com.zjs.example.doubanz.base.BaseFragment;
import com.zjs.example.doubanz.bean.Subjects;
import com.zjs.example.doubanz.bean.Top250Movie;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class Top250MoviesFragment extends BaseFragment implements Top250MovieContract.View, NestedScrollView.OnScrollChangeListener {

    private static final String TAG = "Top250MoviesFragment";

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

    RecyclerAdapter<Subjects> mRecyclerAdapter;
    private Top250MovieContract.Presenter mPresenter;

    public Top250MoviesFragment() {

        mRecyclerAdapter = new RecyclerAdapter<Subjects>() {
            @Override
            protected int getItemViewType(int position, Subjects movie) {
                return R.layout.item_recycler_top250;
            }

            @Override
            protected ViewHolder onCreateViewHolder(View root, int viewType) {
                return new Top250MoviesFragment.ViewHolder(root);
            }
        };

        Log.d(TAG, "Top250MoviesFragment: ");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_top250;
    }

    @Override
    protected void initData() {
        mPresenter.start();
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

    }

    @Override
    public void hideLoadMore() {

    }

    @Override
    public void showNoMoreView() {

    }

    @Override
    public void showMoreData(List<Subjects> list) {
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

    class ViewHolder extends RecyclerAdapter.ViewHolder<Subjects> {

        @BindView(R.id.iv_movie_poster)
        ImageView moviePoster;
        @BindView(R.id.tv_movie_title)
        TextView movieTitle;
        @BindView(R.id.tv_movie_star)
        TextView movieStar;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Subjects movie) {
            Glide.with(Top250MoviesFragment.mContext)
                    .load(movie.getImages().getSmall())
                    .into(moviePoster);
            movieTitle.setText(movie.getTitle());
            movieStar.setText("" + movie.getRating().getAverage());
        }
    }
}
