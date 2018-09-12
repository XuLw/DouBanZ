package com.zjs.example.doubanz.base;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.zjs.example.doubanz.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    //@BindView(R.id.root_layout_base)
    protected RelativeLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mRootLayout = (RelativeLayout) findViewById(R.id.root_layout_base);
        if (initArgs(savedInstanceState)) {
            mRootLayout.addView(initContentView(), new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            ButterKnife.bind(this);
            initOptions();
        } else {
            finish();
        }
    }


    /**
     * 初始化从其他Activity传过来的参数
     *
     * @param bundle
     * @return 参数错误则返回false，即没必要进行下一步界面的初始化，正确则返回true，默认返回true
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 进行一些初始化设置，默认为空，子类需要设置则由子类重写
     */
    protected void initOptions() {

    }

    /**
     * 设置内容布局，不同子类有不同的布局，由子类来完成
     *
     * @return 返回一个view
     */
    protected abstract View initContentView();

    /**
     * 点击导航返回按钮时则finish当前Activity
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}

