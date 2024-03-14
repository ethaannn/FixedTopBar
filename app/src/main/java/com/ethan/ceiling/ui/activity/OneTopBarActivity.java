package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ethan.ceiling.adapter.NormalAdapter;
import com.ethan.ceiling.databinding.ActivityOneBarBinding;
import com.ethan.ceiling.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongxin on 2019/1/21
 */
public class OneTopBarActivity extends AppCompatActivity {



    private List<String> list;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int topHeight;
    private ActivityOneBarBinding mBinding;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, OneTopBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOneBarBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initList();
        initView();
    }

    private void initView() {
        topHeight = ViewUtils.dip2px(this, 200);

        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);

        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mBinding.recyclerView.setNestedScrollingEnabled(false);
        mBinding.recyclerView.setAdapter(normalAdapter);
        mBinding.scrollView.setScrollChangeListener((x, y, oldX, oldY) -> {
            if (y >= topHeight) {
                if (mBinding.includeInsideFixedBar.rlInsideFixed.getParent() != mBinding.llFixedParent) {
                    mBinding.insideFixedBarParent.removeView(mBinding.includeInsideFixedBar.rlInsideFixed);
                    mBinding.llFixedParent.addView(mBinding.includeInsideFixedBar.rlInsideFixed);
                    mBinding.recyclerView.setNestedScrollingEnabled(true);

                }
            } else {
                if (mBinding.includeInsideFixedBar.rlInsideFixed.getParent() != mBinding.insideFixedBarParent) {
                    mBinding.llFixedParent.removeView(mBinding.includeInsideFixedBar.rlInsideFixed);
                    mBinding.insideFixedBarParent.addView(mBinding.includeInsideFixedBar.rlInsideFixed);
                    mBinding.recyclerView.setNestedScrollingEnabled(false);
                }
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
