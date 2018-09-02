package com.zjs.example.doubanz.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.ScanActivity;
import com.zjs.example.doubanz.TestFragment;
import com.zjs.example.doubanz.Top250.Top250MoviePresenter;
import com.zjs.example.doubanz.Top250.Top250MoviesFragment;
import com.zjs.example.doubanz.adapter.FragmentAdapter;
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
import com.zjs.example.doubanz.transformer.RotatePageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CAMERA = 3;

    //    @BindView(R.id.fl_container)
//    FrameLayout mFragmentContainer;
//    @BindView(R.id.bnb_navigation_bar)
//    BottomNavigationBar mNavigationBar;
    @BindView(R.id.vp_container)
    ViewPager mViewPager;

    private Top250MoviesFragment mFragment1;
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

    }

    private void initOptions() {
        mFragment1 = new Top250MoviesFragment();
        mFragment2 = TestFragment.newInstance("fragment " + indexOfFragment++);
        mFragment3 = TestFragment.newInstance("fragment " + indexOfFragment++);
        List<Fragment> data = new ArrayList<>();
        data.add(mFragment1);
        data.add(mFragment2);
        data.add(mFragment3);

        new Top250MoviePresenter(mFragment1);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(data);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new RotatePageTransformer());
        initWidegt();
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

}
