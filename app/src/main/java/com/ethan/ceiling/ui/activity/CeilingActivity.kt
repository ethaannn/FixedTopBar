package com.ethan.ceiling.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ethan.ceiling.databinding.ActivityCeilingBinding
import com.ethan.ceiling.ui.adapter.AdapterMallViewPage2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.uhsk.kit.android.dp2px
import io.github.uhsk.kit.android.sp2px

class CeilingActivity : AppCompatActivity() {
    private var mBinding: ActivityCeilingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCeilingBinding.inflate(layoutInflater)
        setContentView(mBinding!!.getRoot())
        initTabLayout()
        initTabLayoutField()
        initViewPager2()
    }

    private val tabList: MutableList<String> = ArrayList()
    private val tabListFixed: MutableList<String> = ArrayList()
    private fun initTabLayout() {
        tabList.add("车U品")
        tabList.add("车生活")
        tabList.add("车相关")
        for (i in tabList.indices) {
            val tab = mBinding!!.tableLayout.newTab()
            val tabView = AppCompatTextView(this)
            tabView.text = tabList[i]
            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX,13.sp2px().toFloat())
            tabView.setLines(1)
            tabView.setTypeface(Typeface.DEFAULT)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.bottomMargin = 2.dp2px()
            layoutParams.topMargin = 3.dp2px()
            tabView.setLayoutParams(layoutParams)
            tab.setCustomView(tabView)
            mBinding!!.tableLayout.addTab(tab)
        }
    }

    private fun initTabLayoutField() {
        tabListFixed.add("户外运动")
        tabListFixed.add("智能生活")
        tabListFixed.add("数码潮玩")
        mBinding!!.tabLayoutFixed.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private var mAdapter: AdapterMallViewPage2? = null
    private fun initViewPager2() {
        mBinding!!.viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mBinding!!.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
        })
        mAdapter = AdapterMallViewPage2(supportFragmentManager, lifecycle, tabListFixed)
        mBinding!!.viewPager2.adapter = mAdapter
        val mMediator = TabLayoutMediator(
            mBinding!!.tabLayoutFixed,
            mBinding!!.viewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            val tabView = AppCompatTextView(this)
            tabView.text = tabListFixed[position]
            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 13.sp2px().toFloat())
            tabView.setLines(1)
            tabView.setTypeface(Typeface.DEFAULT)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            tabView.setLayoutParams(layoutParams)
            tab.setCustomView(tabView)
        }
        mMediator.attach()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CeilingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
