package com.zjs.example.doubanz.collectMovie;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.adapter.RecyclerAdapter;
import com.zjs.example.doubanz.base.BaseFragment;
import com.zjs.example.doubanz.bean.MovieData;

import java.util.List;

import butterknife.BindView;

public class CollectMovieFragment extends BaseFragment implements CollectMovieContract.View {

    private static final String TAG = "CollectMovieFragment";

    @BindView(R.id.rv_collectMovie)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_movie_collect_empty)
    LinearLayout mEmptyLayout;

    private CollectMovieContract.Presenter mPresenter;
    private RecyclerAdapter<MovieData> mRecyclerAdapter;


    public CollectMovieFragment() {
        mRecyclerAdapter = new RecyclerAdapter<MovieData>() {
            @Override
            protected int getItemViewType(int position, MovieData movieData) {
                return R.layout.item_recycler_movie_collect;
            }

            @Override
            protected ViewHolder<MovieData> onCreateViewHolder(View root, int viewType) {
                return new CollectMovieFragment.ViewHolder(root);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ffff");
        mRecyclerAdapter.clear();
        mPresenter.start();
    }

    @Override
    protected void initData() {
        Log.d(TAG, "initData: fff");
    }

    @Override
    protected void initWidget(View view) {
        Log.d(TAG, "initWidget: ffff");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void setPresenter(CollectMovieContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void showData(List<MovieData> movies) {
        mRecyclerAdapter.add(movies);
    }

    @Override
    public void showEmpty() {
        mEmptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        mEmptyLayout.setVisibility(View.GONE);
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<MovieData> {

        @BindView(R.id.tv_movie_collect_title)
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(MovieData movieData) {
            titleTv.setText(movieData.getTitle());
        }
    }
}
