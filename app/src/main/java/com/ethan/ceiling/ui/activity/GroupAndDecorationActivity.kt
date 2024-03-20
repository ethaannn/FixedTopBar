package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ethan.ceiling.adapter.GroupAdapter;
import com.ethan.ceiling.bean.TestData;
import com.ethan.ceiling.databinding.ActivityGroupAndDecorationBinding;
import com.ethan.ceiling.widget.GroupDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lihongxin on 2019/1/22
 */
public class GroupAndDecorationActivity extends AppCompatActivity {

    private List<TestData> testDataList;
    private LinearLayoutManager linearLayoutManager;
    private GroupAdapter groupAdapter;

    private ActivityGroupAndDecorationBinding mBinding;

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, GroupAndDecorationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =ActivityGroupAndDecorationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initData();
        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        groupAdapter = new GroupAdapter(testDataList);
        mBinding.rvList.addItemDecoration(new GroupDecoration(this));
        mBinding.rvList.setLayoutManager(linearLayoutManager);
        mBinding.rvList.setAdapter(groupAdapter);


    }

    private void initData() {
        testDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            testDataList.add(new TestData(String.format("第一组%d号", i + 1), "第一组"));
        }
        for (int i = 0; i < 8; i++) {
            testDataList.add(new TestData(String.format("第二组%d号", i + 1), "第二组"));
        }
        for (int i = 0; i < 10; i++) {
            testDataList.add(new TestData(String.format("第三组%d号", i + 1), "第三组"));
        }
        for (int i = 0; i < 12; i++) {
            testDataList.add(new TestData(String.format("第四组%d号", i + 1), "第四组"));
        }
    }
}
