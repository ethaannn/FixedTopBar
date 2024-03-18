package com.ethan.ceiling.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ethan.ceiling.R
import com.ethan.ceiling.adapter.AdapterBanner
import com.ethan.ceiling.adapter.AdapterMallViewPage2
import com.ethan.ceiling.bean.BeanBanner
import com.ethan.ceiling.common.ARG_PARAM1
import com.ethan.ceiling.common.ARG_PARAM2
import com.ethan.ceiling.databinding.FragmentRideCarBinding
import com.ethan.ceiling.event.EventAppBarOffsetChanged
import com.ethan.ceiling.util.ViewUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.common.eventbus.EventBus
import com.youth.banner.indicator.RectangleIndicator
import io.github.uhsk.kit.android.sp2px
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@Suppress("UnstableApiUsage")
class FragmentRideCar : Fragment(), KoinComponent {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mBinding: FragmentRideCarBinding
    private val mGuavaEventBus : EventBus by inject()
    private val mSubTabList = arrayListOf("新品上架", "户外运动", "禅茶一味", "亲自生活", "出行指南", "用车无忧")

    private val mBannerList = arrayListOf(
        BeanBanner(imgUrl = "https://p3.dcarimg.com/img/tos-cn-i-dcdx/c194cffd1d394617a1aaf2a853316759~1200x0.png", title = "问界M7赛力斯汽车/中大型SUV"),
        BeanBanner(imgUrl = "https://p6-dcd-sign.byteimg.com/tos-cn-i-6w9my0ksvp/3d03f31116dc44baa979a82866349cdb~noop.webp?lk3s=125a4689&x-expires=1713011200&x-signature=KFnzgxb2rBRs25%2BNT6UX0vigdrU%3D", title = "小米SU7将于3月28日上市，售价多少合适?"),
        BeanBanner(imgUrl = "https://p9-dcd-sign.byteimg.com/tos-cn-i-qvj2lq49k0/ff29b954634f4446b116c17eeff3394d~noop.webp?lk3s=125a4689&x-expires=1713011244&x-signature=31seY5v1BbIelLyH4e6mvtIQyxo%3D", title = "领克汽车官宣降价")
    )
    private val mBannerAdapter = AdapterBanner(mBannerList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mGuavaEventBus.register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_ride_car, container, false)
        mBinding = FragmentRideCarBinding.bind(rootView)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initViewPager2()
        initListener()
    }
    private fun initListener(){
        mBinding.appbar.addOnOffsetChangedListener { _, i ->
            mGuavaEventBus.post(EventAppBarOffsetChanged(offset = i))
        }
    }

    private fun initBanner() {
        mBinding.banner.apply {
            setAdapter(mBannerAdapter)
            addBannerLifecycleObserver(this@FragmentRideCar)
            indicator = RectangleIndicator(this.context)
            setIndicatorRadius(4)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragmentRideCar().apply {
            arguments = Bundle().apply {
               this.putString(ARG_PARAM1, param1)
               this.putString(ARG_PARAM2, param2)
            }
        }
    }

    private val mCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val tabCount = mBinding.tabLayout.tabCount
            for (i in 0 until tabCount) {
                val tab = mBinding.tabLayout.getTabAt(i)
                val tabView = tab!!.customView as AppCompatTextView? ?: return
                if (tab.position == position) {
                    tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16.sp2px().toFloat())
                    tabView.setTypeface(Typeface.DEFAULT_BOLD)
                    tabView.setLayoutParams(LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                } else {
                    tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16.sp2px().toFloat())
                    tabView.setTypeface(Typeface.DEFAULT)
                    tabView.setLayoutParams(LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                }
            }
        }
    }

    private fun initViewPager2() {
        mBinding.viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mBinding.viewPager2.registerOnPageChangeCallback(mCallback)
        val mAdapter = AdapterMallViewPage2(childFragmentManager, lifecycle, mSubTabList)
        mBinding.viewPager2.adapter = mAdapter
        val mMediator = TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager2) { tab: TabLayout.Tab, position: Int ->
            val tabView = AppCompatTextView(mBinding.tabLayout.context)
            tabView.text = mSubTabList[position]
            tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.sp2px(13f).toFloat())
            tabView.setLines(1)
            tabView.setTypeface(Typeface.DEFAULT)
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            tabView.setLayoutParams(layoutParams)
            tab.setCustomView(tabView)
        }
        mMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.viewPager2.unregisterOnPageChangeCallback(mCallback)
        mGuavaEventBus.unregister(this)
    }
}