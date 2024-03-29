package com.ethan.ceiling.ui.fragment

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ethan.ceiling.R
import com.ethan.ceiling.ui.adapter.AdapterMallTopTab
import com.ethan.ceiling.common.ARG_PARAM1
import com.ethan.ceiling.common.ARG_PARAM2
import com.ethan.ceiling.databinding.FragmentMallBinding
import com.ethan.ceiling.event.EventAppBarOffsetChanged
import com.ethan.ceiling.manager.ILoggerManager
import com.ethan.ceiling.manager.IRuntimeManager
import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import io.github.uhsk.kit.android.dp2px
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import kotlin.math.abs


@Suppress(names = ["UnstableApiUsage"])
class FragmentMall : Fragment(), KoinComponent {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mBinding: FragmentMallBinding
    private val mGuavaEventBus: EventBus by inject()
    private val mTabList = arrayListOf("驾乘用车", "用车服务", "智能生活")
    private val mFragmentHelper = FragmentContainerHelper()
    private val mAdapter: AdapterMallTopTab by lazy { AdapterMallTopTab(mTabList) }
    private val mFragments by lazy { arrayListOf(FragmentRideCar.newInstance(param1 = "驾乘用车", param2 = ""), FragmentCarService.newInstance(param1 = "用车服务", param2 = ""), FragmentSmartLife.newInstance(param1 = "智能生活", param2 = "")) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mGuavaEventBus.register(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragmentMall().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_mall, container, false)
        mBinding = FragmentMallBinding.bind(rootView)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.layoutParentTabSegment.apply {
            layoutParams.apply {
                setPadding(0, get<IRuntimeManager>().statusBarHeight, 0, 0)
                get<ILoggerManager>().init()

            }
        }
        initMagicIndicator()
        switchPages(0)
    }

    private fun initMagicIndicator() {
        val commonNavigator = CommonNavigator(requireContext())
        commonNavigator.isAdjustMode = false
        commonNavigator.adapter = mAdapter
        commonNavigator.scrollPivotX = 0.25F
        mBinding.magicIndicator.navigator = commonNavigator
        mFragmentHelper.attachMagicIndicator(mBinding.magicIndicator)
        mAdapter.setOnTabItemClickListener(object : AdapterMallTopTab.OnTabItemClickListener {
            override fun onClick(view: View, position: Int) {
                mFragmentHelper.handlePageSelected(position, true)
                switchPages(i = position)
            }
        })
    }

    @Subscribe
    private fun onGuavaEventBus(event: EventAppBarOffsetChanged) {
        val mHeight: Float = 300.dp2px().toFloat()
        val mOffsetY = abs(event.offset).toFloat()
        val scale: Float = if (mOffsetY / mHeight > 1) 1.toFloat() else mOffsetY / mHeight
        val alpha: Int = (scale * 255).toInt()
        mBinding.layoutParentTabSegment.setBackgroundColor(Color.argb(alpha, 255, 255, 255))
        if (event.scrollOrientation == 0) {
            val color = ArgbEvaluator().evaluate(scale, Color.argb(255, 255, 255, 255), Color.argb(255, 34, 22, 19)) as Int
            mBinding.imgShoppingCar.setColorFilter(color)
            mBinding.imgShoppingCar.setColorFilter(color)
        } else {
            val color = ArgbEvaluator().evaluate(1 - scale, Color.argb(255, 34, 22, 19), Color.argb(255, 255, 255, 255)) as Int
            mBinding.imgShoppingCar.setColorFilter(color)
            mBinding.imgSearch.setColorFilter(color)
        }
    }

    private fun switchPages(i: Int) {
        val fm = childFragmentManager
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

    override fun onDestroy() {
        super.onDestroy()
        mGuavaEventBus.unregister(this)
    }
}