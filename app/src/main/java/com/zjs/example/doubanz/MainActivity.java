package com.zjs.example.doubanz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zjs.example.doubanz.bean.Book;
import com.zjs.example.doubanz.bean.Movie;
import com.zjs.example.doubanz.bean.SearchBook;
import com.zjs.example.doubanz.bean.SearchMovie;
import com.zjs.example.doubanz.bean.SearchType;
import com.zjs.example.doubanz.bean.Subjects;
import com.zjs.example.doubanz.bean.Top250Movie;
import com.zjs.example.doubanz.net.ApiMethods;
import com.zjs.example.doubanz.net.MyObserver;
import com.zjs.example.doubanz.net.ObserverOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CAMERA = 3;

    @BindView(R.id.fl_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bnb_navigation_bar)
    BottomNavigationBar mNavigationBar;

    private TestFragment mFragment1;
    private TestFragment mFragment2;
    private TestFragment mFragment3;

    private int indexOfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indexOfFragment = 0;
        ButterKnife.bind(this);
        initOptions();
    }

    private void initWidegt() {
        mNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);

        mNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_bar_book, "Top250"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_bar_friend, "MyFavo"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_bar_location, "Search"))
                .setBarBackgroundColor(R.color.white)
                .setActiveColor(R.color.colorPrimary)
                .setInActiveColor(R.color.secondary_text)
                .setFirstSelectedPosition(1)
                .initialise();

        mNavigationBar.setTabSelectedListener(this);
    }

    private void initOptions() {
        mFragment1 = TestFragment.newInstance("fragment " + indexOfFragment++);
        mFragment2 = TestFragment.newInstance("fragment " + indexOfFragment++);
        mFragment3 = TestFragment.newInstance("fragment " + indexOfFragment++);
        initWidegt();
        this.setDefaultFragment();
    }

    @Override
    public void onClick(View v) {

//        测试电影搜索
        MyObserver<SearchMovie> searchMovieMyObserver = new MyObserver<>(this, new ObserverOnNextListener<SearchMovie>() {
            @Override
            public void onNext(SearchMovie movie) {
                Log.d(TAG, "电影搜索  onNext: " + "  " + movie.getCount());
                Log.d(TAG, "onNext: search size : " + movie.getSubjects().size());
                for (Subjects subject : movie.getSubjects()) {
                    Log.d(TAG, "onNext: 1111 " + subject.toString());
                }
            }
        });

        ApiMethods.searchMovie(searchMovieMyObserver, SearchType.KEYWORD, "张艺谋", 0, 10);

        ApiMethods.searchMovie(searchMovieMyObserver, SearchType.TAG, "喜剧", 0, 10);


        //测试获取 Top250的电影
        MyObserver<Top250Movie> top250MovieMyObserver = new MyObserver<>(this, new ObserverOnNextListener<Top250Movie>() {
            @Override
            public void onNext(Top250Movie o) {
                Log.d(TAG, "Top250 电影 onNext: " + o.getCount());
                for (Subjects subject : o.getSubjects()) {
                    Log.d(TAG, "onNext: " + subject.toString());
                }
            }
        });

        ApiMethods.getTopMovie(top250MovieMyObserver, 0, 10);

        MyObserver<Movie> getMovieObserver = new MyObserver<Movie>(this, new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "电影详情 onNext: " + movie.toString());
            }
        });

        ApiMethods.getMovieById(getMovieObserver, "1292052");

        // 图书搜索测试
        MyObserver<SearchBook> searchBookMyObserver = new MyObserver<SearchBook>(this, new ObserverOnNextListener<SearchBook>() {
            @Override
            public void onNext(SearchBook searchBook) {
                Log.d(TAG, "搜索图书 onNext: " + searchBook.toString());

                for (Book book : searchBook.getBooks()) {
                    Log.d(TAG, "Book : " + book);
                }
            }
        });

        ApiMethods.searchBook(searchBookMyObserver, "安妮宝贝", 0, 5);

        scanBarCodeCheck();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanBarCode();
                } else {
                    Toast.makeText(this, "被拒绝了...", Toast.LENGTH_SHORT);
                }
                break;
            default:
                break;
        }
    }

    private void scanBarCodeCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            scanBarCode();
        }
    }

    /**
     * 扫描条形码
     */
    private void scanBarCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt("请扫描ISBN条形码");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null)
            Log.d(TAG, "onActivityResult: " + scanResult.getContents());
    }

    private void setDefaultFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mFragment2 == null) {
            mFragment2 = TestFragment.newInstance("fragment " + indexOfFragment++);
        }
        fragmentTransaction.replace(R.id.fl_container, mFragment2);
        fragmentTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                if (mFragment1 == null) {
                    mFragment1 = TestFragment.newInstance("fragment " + indexOfFragment++);
                }
                fragmentTransaction.replace(R.id.fl_container, mFragment1);
                break;
            case 1:
                if (mFragment2 == null) {
                    mFragment2 = TestFragment.newInstance("fragment " + indexOfFragment++);
                }
                fragmentTransaction.replace(R.id.fl_container, mFragment2);
                break;
            case 2:
                if (mFragment3 == null) {
                    mFragment3 = TestFragment.newInstance("fragment " + indexOfFragment++);
                }
                fragmentTransaction.replace(R.id.fl_container, mFragment3);
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
