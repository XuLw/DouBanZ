package com.zjs.example.doubanz.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zjs.example.doubanz.R;
import com.zjs.example.doubanz.ScanActivity;
import com.zjs.example.doubanz.TestFragment;
import com.zjs.example.doubanz.Top250.Top250MoviePresenter;
import com.zjs.example.doubanz.Top250.Top250MoviesFragment;
import com.zjs.example.doubanz.adapter.FragmentAdapter;
import com.zjs.example.doubanz.base.BaseActivity;
import com.zjs.example.doubanz.collectMovie.CollectMovieFragment;
import com.zjs.example.doubanz.collectMovie.CollectMoviePresenter;
import com.zjs.example.doubanz.transformer.RotatePageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CAMERA = 3;

    @BindView(R.id.vp_container)
    ViewPager mViewPager;

    private Top250MoviesFragment mTop250MoviesFragment;
    private CollectMovieFragment mCollectMovieFragment;

    @Override
    protected void initOptions() {

        mTop250MoviesFragment = new Top250MoviesFragment();
        mCollectMovieFragment = new CollectMovieFragment();
        List<Fragment> data = new ArrayList<>();
        data.add(mTop250MoviesFragment);
        data.add(mCollectMovieFragment);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(data);

        new Top250MoviePresenter(mTop250MoviesFragment);
        new CollectMoviePresenter(mCollectMovieFragment);

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new RotatePageTransformer());

    }

    @Override
    protected View initContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
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
