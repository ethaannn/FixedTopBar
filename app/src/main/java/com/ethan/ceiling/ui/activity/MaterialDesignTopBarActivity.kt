package com.ethan.ceiling.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.databinding.ActivityMaterialDesignBinding
import com.ethan.ceiling.ui.adapter.NormalAdapter
import org.koin.core.component.KoinComponent

class MaterialDesignTopBarActivity : AppCompatActivity() ,KoinComponent{
    private var list: MutableList<String> = arrayListOf()
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var mBinding: ActivityMaterialDesignBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMaterialDesignBinding.inflate(
            layoutInflater
        )
        setContentView(mBinding!!.getRoot())
        initList()
        initView()
    }

    private fun initList() {

        for (i in 1..100) {
            list.add(i.toString() + "")
        }
    }

    private fun initView() {
        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)
        mBinding!!.recyclerView.setLayoutManager(linearLayoutManager)
        mBinding!!.recyclerView.isNestedScrollingEnabled = false
        mBinding!!.recyclerView.setAdapter(normalAdapter)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MaterialDesignTopBarActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
