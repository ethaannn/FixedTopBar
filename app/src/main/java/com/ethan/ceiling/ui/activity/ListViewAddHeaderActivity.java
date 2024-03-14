package com.ethan.ceiling.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ethan.ceiling.R;
import com.ethan.ceiling.adapter.ListViewAdapter;
import com.ethan.ceiling.databinding.ActivityListViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongxin on 2019/1/18
 */
public class ListViewAddHeaderActivity extends AppCompatActivity {
    private View mHeaderView;
    private View mTopBarView;
    private ListViewAdapter mAdapter;
    private List<String> list;

    private ActivityListViewBinding mBinding;
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ListViewAddHeaderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initList();
        initView();
    }

    private void initView() {


        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header, mBinding.lv, false);
        mTopBarView = LayoutInflater.from(this).inflate(R.layout.inside_fixed_bar, mBinding.lv, false);

        mBinding.lv.addHeaderView(mHeaderView);
        mBinding.lv.addHeaderView(mTopBarView);
        mAdapter = new ListViewAdapter(list);
        mBinding.lv.setAdapter(mAdapter);
        mBinding.lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /* 判断ListView头部(mHeaderView)当前是否可见
                 * 来决定隐藏或显示浮动栏(mFloatBar)*/
                if (firstVisibleItem >= 1) {
                    mBinding.flOutsideFixed.setVisibility(View.VISIBLE);
                } else {
                    mBinding.flOutsideFixed.setVisibility(View.GONE);
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
