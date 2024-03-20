package com.ethan.ceiling.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.R
import com.ethan.ceiling.databinding.ActivityDecorationBinding
import com.ethan.ceiling.ui.adapter.NormalAdapter
import com.ethan.ceiling.ui.widget.FixedBarDecoration

class DecorationActivity : AppCompatActivity() {
    private var headerView: View? = null
    private var list: MutableList<String>  = arrayListOf()
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var mBinding: ActivityDecorationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDecorationBinding.inflate(layoutInflater)
        setContentView(mBinding!!.getRoot())
        initList()
        initView()
    }

    private fun initView() {
        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)
        mBinding!!.rvList.addItemDecoration(FixedBarDecoration(this))
        mBinding!!.rvList.setLayoutManager(linearLayoutManager)
        mBinding!!.rvList.setAdapter(normalAdapter)
        headerView = LayoutInflater.from(this).inflate(R.layout.header, mBinding!!.rvList, false)
        normalAdapter!!.setmHeaderView(headerView)
    }

    private fun initList() {
        for (i in 1..100) {
            list.add(i.toString() + "")
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, DecorationActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
