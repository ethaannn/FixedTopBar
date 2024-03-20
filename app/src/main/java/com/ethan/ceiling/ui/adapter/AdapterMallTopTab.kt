package com.ethan.ceiling.ui.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.ethan.ceiling.R
import com.ethan.ceiling.databinding.LayoutMallTopTabItemBinding
import io.github.uhsk.kit.android.dp2px
import io.github.uhsk.kit.android.sp2px
import io.github.uhsk.kit.asColor
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class AdapterMallTopTab(val list: ArrayList<String>) : CommonNavigatorAdapter() {


    override fun getCount(): Int = list.size
    override fun getTitleView(context: Context?, position: Int): IPagerTitleView {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_mall_top_tab_item, null, false)
        val mBinding: LayoutMallTopTabItemBinding = LayoutMallTopTabItemBinding.bind(rootView)
        mBinding.tabItem.text = list[position]
        val titleView = CommonPagerTitleView(context)

        return titleView.apply {
            setContentView(mBinding.root)
            onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {
                override fun onSelected(p0: Int, p1: Int) {
                    mBinding.tabItem.setTextColor(R.color.color_3BBECC.asColor(context=context!!))
                    mBinding.tabItem.setTextSize(TypedValue.COMPLEX_UNIT_PX, 15.sp2px().toFloat())
                }

                override fun onDeselected(p0: Int, p1: Int) {
                    mBinding.tabItem.setTextColor(R.color.color_221613.asColor(context = context!!))
                    mBinding.tabItem.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14.sp2px().toFloat())
                }

                override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {}

                override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {}

            }
            setOnClickListener {
                if (this@AdapterMallTopTab::onTabItemClickListener.isInitialized) {
                    onTabItemClickListener.onClick(it, position = position)
                }
            }
        }
    }

    private lateinit var onTabItemClickListener: OnTabItemClickListener
    fun setOnTabItemClickListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    interface OnTabItemClickListener {
        fun onClick(view: View, position: Int)
    }

    override fun getIndicator(context: Context?): IPagerIndicator {

        return LinePagerIndicator(context).apply {
            setColors(R.color.color_3BBECC.asColor(context!!))
            lineHeight = 3.dp2px().toFloat()
            mode = LinePagerIndicator.MODE_WRAP_CONTENT
            xOffset = 15.dp2px().toFloat()
        }
    }
}