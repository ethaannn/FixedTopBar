package com.ethan.ceiling.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import com.ethan.ceiling.adapter.AdapterCategoryPager2;
import com.ethan.ceiling.databinding.ActivityCeilingBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ethan.ceiling.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class CeilingActivity extends AppCompatActivity {
    private ActivityCeilingBinding mBinding;


    public static void start(Context context) {
        Intent intent = new Intent(context, CeilingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCeilingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initTabLayout();
        initTabLayoutField();
        initViewPager2();
    }

    private final List<String> tabList = new ArrayList<>();
    private final List<String> tabListFixed=new ArrayList<>();

    private void initTabLayout() {
        tabList.add("车U品");
        tabList.add("车生活");
        tabList.add("车相关");
        for (int i = 0; i < tabList.size(); i++) {
            TabLayout.Tab tab = mBinding.tableLayout.newTab();
            AppCompatTextView tabView = new AppCompatTextView(this);
            tabView.setText(tabList.get(i));
            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.sp2px(13F));
            tabView.setLines(1);
            tabView.setTypeface(Typeface.DEFAULT);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = ViewUtils.dp2px(2);
            layoutParams.topMargin = ViewUtils.dp2px(3);
            tabView.setLayoutParams(layoutParams);
            tab.setCustomView(tabView);
            mBinding.tableLayout.addTab(tab);
        }


    }
    private void initTabLayoutField(){

        tabListFixed.add("户外运动");
        tabListFixed.add("智能生活");
        tabListFixed.add("数码潮玩");

//        for (int i = 0; i < tabListFixed.size(); i++) {
//            TabLayout.Tab tab = mBinding.tabLayoutFixed.newTab();
//            AppCompatTextView tabView = new AppCompatTextView(this);
//            tabView.setText(tabList.get(i));
//            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.sp2px(13F));
//            tabView.setLines(1);
//            tabView.setTypeface(Typeface.DEFAULT);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.bottomMargin = ViewUtils.dp2px(2);
//            layoutParams.topMargin = ViewUtils.dp2px(3);
//            tabView.setLayoutParams(layoutParams);
//            tab.setCustomView(tabView);
//            mBinding.tabLayoutFixed.addTab(tab);
//        }
        mBinding.tabLayoutFixed.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private AdapterCategoryPager2 mAdapter;
    private void initViewPager2(){
        mBinding.viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        mAdapter =new AdapterCategoryPager2(getSupportFragmentManager(),getLifecycle(),tabListFixed);
        mBinding.viewPager2.setAdapter(mAdapter);
        TabLayoutMediator mMediator = new TabLayoutMediator(mBinding.tabLayoutFixed, mBinding.viewPager2, (tab, position) -> {
            AppCompatTextView tabView = new AppCompatTextView(this);
            tabView.setText(tabListFixed.get(position));
            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.sp2px(13));
            tabView.setLines(1);
            tabView.setTypeface(Typeface.DEFAULT);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tabView.setLayoutParams(layoutParams);
            tab.setCustomView(tabView);
        });
        mMediator.attach();

    }
}
