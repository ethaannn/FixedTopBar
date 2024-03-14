package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ethan.ceiling.adapter.NormalAdapter;
import com.ethan.ceiling.databinding.ActivityMaterialDesignBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lihongxin on 2019/1/21
 */
public class MaterialDesignTopBarActivity extends AppCompatActivity {
    private List<String> list;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager           linearLayoutManager;
    private ActivityMaterialDesignBinding mBinding;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MaterialDesignTopBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMaterialDesignBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initList();
        initView();
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);
        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mBinding.recyclerView.setNestedScrollingEnabled(false);
        mBinding.recyclerView.setAdapter(normalAdapter);
    }
}
