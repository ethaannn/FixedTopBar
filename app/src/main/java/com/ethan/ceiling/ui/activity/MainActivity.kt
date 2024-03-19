package com.ethan.ceiling.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ethan.ceiling.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public void onClickTwoTopBar(View view) {
        TwoSameTopBarActivity.start(this);
    }

    public void onClickOneTopBar(View view) {
        OneTopBarActivity.start(this);
    }

    public void onClickListView(View view) {
        ListViewAddHeaderActivity.start(this);
    }

    public void onClickMaterialDesign(View view) {
        MaterialDesignTopBarActivity.start(this);
    }

    public void onClickDecoration(View view) {
        DecorationActivity.start(this);
    }

    public void onClickGroupAndDecoration(View view) {
        GroupAndDecorationActivity.start(this);
    }

    public void onClickMaterialDesign2(View view) {
        CeilingActivity.start(view.getContext());
    }

    public void onComplexCeilings(View view) {
        ActivityCeilingDouble.Companion.start(view.getContext());
    }
}

