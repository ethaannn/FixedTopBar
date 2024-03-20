package com.ethan.ceiling.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.bean.TestData
import com.ethan.ceiling.databinding.ActivityGroupAndDecorationBinding
import com.ethan.ceiling.ui.adapter.GroupAdapter
import com.ethan.ceiling.ui.widget.GroupDecoration


class GroupAndDecorationActivity : AppCompatActivity() {
    private var testDataList: MutableList<TestData>  = arrayListOf()
    private var linearLayoutManager: LinearLayoutManager? = null
    private var groupAdapter: GroupAdapter? = null
    private var mBinding: ActivityGroupAndDecorationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGroupAndDecorationBinding.inflate(
            layoutInflater
        )
        setContentView(mBinding!!.getRoot())
        initData()
        initView()
    }

    private fun initView() {
        linearLayoutManager = LinearLayoutManager(this)
        groupAdapter = GroupAdapter(testDataList)
        mBinding!!.rvList.addItemDecoration(GroupDecoration(this))
        mBinding!!.rvList.setLayoutManager(linearLayoutManager)
        mBinding!!.rvList.setAdapter(groupAdapter)
    }

    private fun initData() {
        testDataList = ArrayList()
        for (i in 0..5) {
            testDataList.add(TestData(String.format("第一组%d号", i + 1), "第一组"))
        }
        for (i in 0..7) {
            testDataList.add(TestData(String.format("第二组%d号", i + 1), "第二组"))
        }
        for (i in 0..9) {
            testDataList.add(TestData(String.format("第三组%d号", i + 1), "第三组"))
        }
        for (i in 0..11) {
            testDataList.add(TestData(String.format("第四组%d号", i + 1), "第四组"))
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, GroupAndDecorationActivity::class.java)
            context.startActivity(intent)
        }
    }
}
