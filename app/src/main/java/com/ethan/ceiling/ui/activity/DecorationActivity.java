package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ethan.ceiling.R;
import com.ethan.ceiling.adapter.NormalAdapter;
import com.ethan.ceiling.databinding.ActivityDecorationBinding;
import com.ethan.ceiling.widget.FixedBarDecoration;

import java.util.ArrayList;
import java.util.List;

public class DecorationActivity extends AppCompatActivity {
    private View                headerView;
    private List<String>        list;
    private NormalAdapter       normalAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ActivityDecorationBinding mBinding;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DecorationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDecorationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initList();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);
        mBinding.rvList.addItemDecoration(new FixedBarDecoration(this));
        mBinding.rvList.setLayoutManager(linearLayoutManager);
        mBinding.rvList.setAdapter(normalAdapter);
        headerView = LayoutInflater.from(this).inflate(R.layout.header, mBinding.rvList, false);
        normalAdapter.setmHeaderView(headerView);


    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }
}
