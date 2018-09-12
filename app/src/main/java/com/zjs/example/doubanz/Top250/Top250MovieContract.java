package com.zjs.example.doubanz.Top250;

import com.zjs.example.doubanz.base.BasePresenter;
import com.zjs.example.doubanz.base.BaseView;
import com.zjs.example.doubanz.bean.MovieSubjects;

import java.util.List;

public interface Top250MovieContract {
    interface View extends BaseView<Presenter> {
        /**
         * 当列表为空时，显示空页面
         */
        void showEmptyView();

        /**
         * 当加载更多数据时，显示加载页面
         */
        void showLoadMoreView();

        /**
         * 隐藏加载更多界面
         */
        void hideLoadMore();

        /**
         * 没有更多数据时显示
         */
        void showNoMoreView();

        /**
         * 显示更多数据
         *
         * @param list
         */
        void showMoreData(List<MovieSubjects> list);
    }

    interface Presenter extends BasePresenter {
        /**
         * 判断是否还有数据
         *
         * @return
         */
        boolean canLoadMore();

        /**
         * 加载更多
         */
        void loadMore();
    }

}
