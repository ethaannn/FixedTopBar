package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ethan.ceiling.R;
import com.ethan.ceiling.adapter.NormalAdapter;
import com.ethan.ceiling.databinding.ActivityTwoBarBinding;
import com.ethan.ceiling.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class TwoSameTopBarActivity extends AppCompatActivity {

    private ActivityTwoBarBinding mBinding;

    private List<String> list;
    private NormalAdapter normalAdapter;
    private int topHeight;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, TwoSameTopBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =ActivityTwoBarBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_two_bar);
        initList();
        initView();
    }

    private void initView() {
        topHeight = ViewUtils.dip2px(this, 200);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        normalAdapter = new NormalAdapter(list);

        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mBinding.recyclerView.setNestedScrollingEnabled(false);
        mBinding.recyclerView.setAdapter(normalAdapter);

        mBinding.scrollView.setScrollChangeListener((x, y, oldX, oldY) -> {
            Log.d("test", "当前x：" + x + "当前y:" + y);
            if (y >= topHeight) {
                //重点 通过距离变化隐藏内外固定栏实现
                mBinding.llOutsideFixed.setVisibility(View.VISIBLE);
                mBinding.insideFixedBar.setVisibility(View.GONE);
                mBinding.recyclerView.setNestedScrollingEnabled(true);
            } else {
                mBinding.llOutsideFixed.setVisibility(View.GONE);
                mBinding.insideFixedBar.setVisibility(View.VISIBLE);
                mBinding.recyclerView.setNestedScrollingEnabled(false);
            }

        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }
}

