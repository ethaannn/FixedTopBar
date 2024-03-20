package com.ethan.ceiling.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ethan.ceiling.R
import com.ethan.ceiling.ui.adapter.AdapterNavigatorBottom
import com.ethan.ceiling.bean.BeanNav
import com.ethan.ceiling.databinding.ActivityCeiling2Binding
import com.ethan.ceiling.ui.fragment.FragmentCommunity
import com.ethan.ceiling.ui.fragment.FragmentMall
import com.ethan.ceiling.ui.fragment.FragmentMine
import com.ethan.ceiling.ui.fragment.FragmentService
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

class ActivityCeilingDouble : AppCompatActivity() {
    private val mNavigation = arrayListOf(
        BeanNav(title = "社区", icon = R.mipmap.ic_launcher),
        BeanNav(title = "商城", icon = R.mipmap.ic_launcher),
        BeanNav(title = "服务", icon = R.mipmap.ic_launcher),
        BeanNav(title = "我的", icon = R.mipmap.ic_launcher)
    )
    private val mFragments = arrayListOf(
        FragmentCommunity.newInstance(param1 = "社区", param2 = ""),
        FragmentMall.newInstance(param1 = "商城", param2 = ""),
        FragmentService.newInstance(param1 = "服务", param2 = ""),
        FragmentMine.newInstance(param1 = "我的", param2 = "")
    )
    private lateinit var mAdapterNavigatorBottom : AdapterNavigatorBottom
    private val mFragmentContainerHelper = FragmentContainerHelper()
    private val mBinding: ActivityCeiling2Binding by lazy {
        ActivityCeiling2Binding.inflate(layoutInflater)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ActivityCeilingDouble::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initMagicIndicator()
        switchPages(0)
    }

    private fun initMagicIndicator() {
        mAdapterNavigatorBottom = AdapterNavigatorBottom(list = mNavigation)
        val commonNavigator =CommonNavigator(this)
        commonNavigator.isAdjustMode =true
        commonNavigator.adapter =mAdapterNavigatorBottom

        mBinding.magicIndicator.apply {
            setBackgroundColor(Color.WHITE)
        }
        mAdapterNavigatorBottom.setOnNavigationClickListener(object : AdapterNavigatorBottom.OnNavigationItemClickListener{
            override fun onClick(view: View,position:Int) {
                mFragmentContainerHelper.handlePageSelected(position,false)
                switchPages(position)
            }
        })
        mBinding.magicIndicator.navigator =commonNavigator
        mFragmentContainerHelper.attachMagicIndicator(mBinding.magicIndicator)
    }

    private fun switchPages(i: Int) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        mFragments.forEachIndexed { index, fragment ->
            if (i != index) {
                if (fragment.isAdded) {
                    ft.hide(fragment)
                }
            }
        }
        val fragment: Fragment = mFragments[i]
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.add(mBinding.fragmentContainerView.id, fragment)
        }
        ft.commitNowAllowingStateLoss()
    }
}