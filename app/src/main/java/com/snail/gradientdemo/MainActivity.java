package com.snail.gradientdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snail.gradientdemo.adapter.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends AppCompatActivity {

    private PtrFrameLayout mPtrFrameLayout;
    private View mSearchBar;

    private int distance, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //让状态栏透明
        StatusBarCompat.translucentStatusBar(this, true);


        //渐变的View
        mSearchBar = findViewById(R.id.searchBarLayout);


        //初始化一些数据
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item-" + i);
        }
        mRecyclerView.setAdapter(new DemoAdapter(mData));


        //下拉刷新
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptrFrameLayout);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });


        //渐变过度的高度
        //alpha 0.0~1.0 换成高度的渐变就是 0~180
        height = (int) (180 * getResources().getDisplayMetrics().density);
        //监听渐变状态
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //distance 滑动的总距离
                //dy 滑动了的距离
                distance += dy;
                if (distance <= 0) {
                    mSearchBar.setAlpha(0.f);
                } else if (distance > 0 && distance <= height) {
                    mSearchBar.setAlpha((float) distance / height);
                } else {
                    mSearchBar.setAlpha(1.0f);
                }
            }
        });
    }
}
