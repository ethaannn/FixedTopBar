package com.ethan.ceiling.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.ethan.ceiling.R
import com.ethan.ceiling.bean.BeanNav
import com.ethan.ceiling.databinding.LayoutNaviItemBinding
import io.github.uhsk.kit.android.view.imageResource
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class AdapterNavigatorBottom(val list:ArrayList<BeanNav>) :CommonNavigatorAdapter(){

    override fun getCount(): Int =list.size

    override fun getTitleView(context: Context?, position: Int): IPagerTitleView {
        val rootView =LayoutInflater.from(context).inflate(R.layout.layout_navi_item,null,false)
        val mBinding:LayoutNaviItemBinding = LayoutNaviItemBinding.bind(rootView)
        mBinding.titleImg.imageResource =list[position].icon
        mBinding.titleText.text =list[position].title
       val titleView =CommonPagerTitleView(context)
        return titleView.apply {
            setContentView(mBinding.root)
            onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {
                override fun onSelected(p0: Int, p1: Int) {
                    mBinding.titleText.setTextColor(Color.BLACK)
                }

                override fun onDeselected(p0: Int, p1: Int) {
                    mBinding.titleText.setTextColor(Color.LTGRAY)
                }

                override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
                    mBinding.titleImg.scaleX = (1.3F + (0.8 - 1.3F) * leavePercent).toFloat()
                    mBinding.titleImg.scaleY = (1.3F + (0.8 - 1.3F) * leavePercent).toFloat()
                }

                override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
                    mBinding.titleImg.scaleX = (0.8F + (1.3F - 0.8F) * enterPercent)
                    mBinding.titleImg.scaleY = (0.8F + (1.3F - 0.8F) * enterPercent)
                }

            }
            setOnClickListener {
                if (this@AdapterNavigatorBottom::onNavigationItemClickListener.isInitialized){
                    onNavigationItemClickListener.onClick(it,position=position)
                }
            }
        }
    }

    private lateinit var  onNavigationItemClickListener:OnNavigationItemClickListener
    fun setOnNavigationClickListener(onNavigationItemClickListener: OnNavigationItemClickListener){
        this.onNavigationItemClickListener =onNavigationItemClickListener
    }
    interface OnNavigationItemClickListener{
        fun onClick(view:View,position:Int)
    }

    override fun getIndicator(context: Context?): IPagerIndicator? {
        return null
    }
}