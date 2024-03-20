package com.ethan.ceiling.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.databinding.ActivityOneBarBinding
import com.ethan.ceiling.ui.adapter.NormalAdapter
import io.github.uhsk.kit.android.dp2px
import org.koin.core.component.KoinComponent


class OneTopBarActivity : AppCompatActivity(), KoinComponent {
    private var list: MutableList<String> = arrayListOf()
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var topHeight = 0
    private var mBinding: ActivityOneBarBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOneBarBinding.inflate(layoutInflater)
        setContentView(mBinding!!.getRoot())
        initList()
        initView()
    }

    private fun initView() {
        topHeight = 200.dp2px()
        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)
        mBinding!!.recyclerView.setLayoutManager(linearLayoutManager)
        mBinding!!.recyclerView.isNestedScrollingEnabled = true
        mBinding!!.recyclerView.setAdapter(normalAdapter)
        //mBinding.scrollView.setScrollChangeListener((x, y, oldX, oldY) -> {
        //    if (y >= topHeight) {
        //        if (mBinding.includeInsideFixedBar.rlInsideFixed.getParent() != mBinding.llFixedParent) {
        //            mBinding.insideFixedBarParent.removeView(mBinding.includeInsideFixedBar.rlInsideFixed);
        //            mBinding.llFixedParent.addView(mBinding.includeInsideFixedBar.rlInsideFixed);
        //            mBinding.recyclerView.setNestedScrollingEnabled(true);
        //
        //        }
        //    } else {
        //        if (mBinding.includeInsideFixedBar.rlInsideFixed.getParent() != mBinding.insideFixedBarParent) {
        //            mBinding.llFixedParent.removeView(mBinding.includeInsideFixedBar.rlInsideFixed);
        //            mBinding.insideFixedBarParent.addView(mBinding.includeInsideFixedBar.rlInsideFixed);
        //            mBinding.recyclerView.setNestedScrollingEnabled(false);
        //        }
        //    }
        //});
    }

    private fun initList() {
        for (i in 1..100) {
            list.add(i.toString() + "")
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, OneTopBarActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
