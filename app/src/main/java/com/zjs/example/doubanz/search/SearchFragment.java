package com.zjs.example.doubanz.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.adapter.RecyclerAdapter;
import com.zjs.example.doubanz.base.BaseApp;
import com.zjs.example.doubanz.base.BaseFragment;
import com.zjs.example.doubanz.bean.BookSubject;
import com.zjs.example.doubanz.bean.MovieSubjects;
import com.zjs.example.doubanz.detail.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment implements SearchContract.View, View.OnClickListener {

    private static final String TAG = "SearchFragment";
    public static final String INTENT_KEY_MOVIE_ID = "movieId";
    public static final String INTENT_KEY_MOVIE_POSTER = "moviePoster";

    @BindView(R.id.et_search_input)
    TextView searchInputTv;
    @BindView(R.id.ibt_search)
    ImageButton searchIbt;
    @BindView(R.id.ll_loading_root)
    LinearLayout loadingRoot;
    @BindView(R.id.iv_search_loading)
    ImageView loadingIv;
    @BindView(R.id.rv_search_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_search_empty_root)
    LinearLayout emptyRoot;

    private SearchContract.Presenter mPresenter;
    private SearchTypeEnum mType;
    private Animation animation;
    private RecyclerAdapter mRecyclerAdapter;

    public SearchFragment() {

    }

    @Override
    protected void initData() {
        animation = AnimationUtils.loadAnimation(mContext, R.anim.search_loading_anim);

    }

    @Override
    protected void initWidget(View view) {
        if (mType == SearchTypeEnum.MOVIE || mType == SearchTypeEnum.MOVIE_TYPE) {
            mRecyclerAdapter = new RecyclerAdapter<MovieSubjects>() {
                @Override
                protected int getItemViewType(int position, MovieSubjects subjects) {
                    return R.layout.item_recycler_movie_search;
                }

                @Override
                protected ViewHolder<MovieSubjects> onCreateViewHolder(View root, int viewType) {
                    return new MovieViewHolder(root);
                }
            };
        } else if (mType == SearchTypeEnum.BOOK) {
            Log.d(TAG, "initData: ");
            mRecyclerAdapter = new RecyclerAdapter<BookSubject>() {

                @Override
                protected int getItemViewType(int position, BookSubject bookSubject) {
                    return R.layout.item_recycler_book_search;
                }

                @Override
                protected ViewHolder<BookSubject> onCreateViewHolder(View root, int viewType) {
                    return new BookViewHolder(root);
                }
            };
            mRecyclerView.getItemAnimator().setChangeDuration(300);
            mRecyclerView.getItemAnimator().setMoveDuration(300);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        searchInputTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = searchInputTv.getText().toString();
                    if (text != null && text.length() != 0) {
                        search(text);
                    }
                }
                return true;
            }
        });
    }

    public void setType(SearchTypeEnum type) {
        mType = type;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void showLoading() {
        emptyRoot.setVisibility(View.GONE);
        loadingRoot.setVisibility(View.VISIBLE);
        loadingIv.setAnimation(animation);
    }

    @Override
    public void hideLoading() {
        loadingRoot.setVisibility(View.GONE);
    }

    @Override
    public void showData(List subjects) {
        mRecyclerAdapter.clear();
        mRecyclerAdapter.add(subjects);
    }

    @Override
    public void showEmpty() {
        emptyRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.ibt_search})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_search:
                String text = searchInputTv.getText().toString();
                if (text != null && text.length() != 0) {
                    search(text);
                }
                break;
        }
    }

    private void search(String text) {
        if (mType == SearchTypeEnum.MOVIE_TYPE) {
            mPresenter.searchMovieType(text);
        } else if (mType == SearchTypeEnum.MOVIE) {
            mPresenter.searchMovie(text);
        } else if (mType == SearchTypeEnum.BOOK) {
            mPresenter.searchBook(text);
        }
    }

    class MovieViewHolder extends RecyclerAdapter.ViewHolder<MovieSubjects> {

        @BindView(R.id.tv_search_movie_directors)
        TextView diretorsTv;
        @BindView(R.id.tv_search_movie_star)
        TextView starTv;
        @BindView(R.id.iv_search_movie_poster)
        ImageView moviePosterIv;
        @BindView(R.id.tv_search_movie_title)
        TextView movieTitleTv;
        @BindView(R.id.rl_search_movie_root)
        RelativeLayout movieSearchRoot;

        public MovieViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(final MovieSubjects subjects) {
            Glide.with(BaseApp.getContext()).load(subjects.getImages().getSmall()).into(moviePosterIv);
            starTv.setText("" + subjects.getRating().getAverage());

            StringBuilder diretors = new StringBuilder();

            int indexOfDiretors = 0;
            for (; indexOfDiretors < subjects.getDirectors().size() - 1; indexOfDiretors++) {
                diretors.append(subjects.getDirectors().get(indexOfDiretors).getName()).append(" / ");
            }
            if (subjects.getDirectors().size() != 0)
                diretors.append(subjects.getDirectors().get(indexOfDiretors).getName());
            else
                diretors.append("unknown");

            diretorsTv.setText(diretors);
            movieTitleTv.setText(subjects.getTitle());

            movieSearchRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra(INTENT_KEY_MOVIE_ID, subjects.getId());
                    intent.putExtra(INTENT_KEY_MOVIE_POSTER, subjects.getImages().getSmall());
                    startActivity(intent);
                }
            });
        }
    }

    class BookViewHolder extends RecyclerAdapter.ViewHolder<BookSubject> {

        @BindView(R.id.iv_search_book_cover)
        ImageView bookCover;
        @BindView(R.id.tv_search_book_author)
        TextView bookAuthorTv;
        @BindView(R.id.tv_search_book_binding)
        TextView bookBindingTv;
        @BindView(R.id.tv_search_book_pubdate)
        TextView bookPubdateTv;
        @BindView(R.id.tv_search_book_page)
        TextView bookPageTv;
        @BindView(R.id.tv_search_book_publisher)
        TextView bookPublisherTv;
        @BindView(R.id.tv_search_book_star)
        TextView bookRatingTv;
        @BindView(R.id.tv_search_book_summary)
        TextView bookSummaryTv;
        @BindView(R.id.tv_search_book_summary_all)
        TextView bookSummaryAllTv;
        @BindView(R.id.tv_search_book_tag)
        TextView bookTagTv;
        @BindView(R.id.tv_search_book_title)
        TextView bookTitleTv;
        @BindView(R.id.ll_search_book_summary_root)
        LinearLayout bookSummaryRoot;

        private boolean isPressed;

        public BookViewHolder(View itemView) {
            super(itemView);
            isPressed = false;
        }

        @Override
        protected void onBind(final BookSubject bookSubject) {
            StringBuilder authors = new StringBuilder();
            int indexOfAuthor = 0;
            for (; indexOfAuthor < bookSubject.getAuthor().size() - 1; indexOfAuthor++) {
                authors.append(bookSubject.getAuthor().get(indexOfAuthor)).append(" / ");
            }
            if (bookSubject.getAuthor().size() != 0)
                authors.append(bookSubject.getAuthor().get(indexOfAuthor));
            else
                authors.append("unknown");

            StringBuilder tags = new StringBuilder();
            int indexOfTag = 0;
            for (; indexOfTag < bookSubject.getTags().size() - 1; indexOfTag++) {
                tags.append(bookSubject.getTags().get(indexOfTag).getName()).append("、");
            }
            if (bookSubject.getTags().size() != 0) {
                tags.append(bookSubject.getTags().get(indexOfTag).getName());
            } else
                tags.append("null");

            Glide.with(BaseApp.getContext()).load(bookSubject.getImage()).into(bookCover);
            bookAuthorTv.setText("作者 : " + authors.toString());
            bookRatingTv.setText("" + bookSubject.getRating().getAverage());
            bookPageTv.setText("页数 : " + bookSubject.getPages());
            bookBindingTv.setText("装帧 : " + bookSubject.getBinding());
            bookPubdateTv.setText("出版时间 : " + bookSubject.getPubdate());
            bookPublisherTv.setText("出版社 : " + bookSubject.getPublisher());
            bookTagTv.setText(tags.toString());
            bookSummaryTv.setText("简介 :  " + bookSubject.getSummary());
            bookSummaryAllTv.setText("简介 :  " + bookSubject.getSummary());
            bookTitleTv.setText(bookSubject.getTitle());

            bookSummaryRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isPressed = !isPressed;
                    if (isPressed) {
                        bookSummaryTv.setVisibility(View.GONE);
                        bookSummaryAllTv.setVisibility(View.VISIBLE);
                    } else {
                        bookSummaryAllTv.setVisibility(View.GONE);
                        bookSummaryTv.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
