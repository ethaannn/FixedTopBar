package com.ethan.ceiling.ui.activity

import android.animation.ArgbEvaluator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.R
import com.ethan.ceiling.databinding.ActivityMaterialDesignBinding
import com.ethan.ceiling.manager.IRuntimeManager
import com.ethan.ceiling.ui.adapter.AdapterNormal
import com.ethan.ceiling.ui.adapter.NormalAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import io.github.uhsk.kit.android.dp2px
import io.github.uhsk.kit.android.setNavigationNoDoubleClickListener
import io.github.uhsk.kit.android.view.backgroundColor
import io.github.uhsk.kit.asColor
import io.github.uhsk.kit.asDrawable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.abs

class ActivityMaterialDesignTopBar : AppCompatActivity(), KoinComponent{
    private var list: MutableList<String> = arrayListOf()
    private lateinit var normalAdapter: AdapterNormal

    private lateinit var mBinding: ActivityMaterialDesignBinding

    private val mRuntimeManager:IRuntimeManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMaterialDesignBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())
        mBinding.toolbar.apply {
            layoutParams.apply { setPadding(0, mRuntimeManager.statusBarHeight, 0, 0) }
            title ="测试标题"
        }
        mBinding.toolbarLayout.setCollapsedTitleTextColor(R.color.common_gray_white.asColor(context = baseContext))
        mBinding.toolbarLayout.setExpandedTitleColor(R.color.common_gray_white.asColor(context = baseContext))

        mBinding.toolbarLayout.apply {
            collapsedTitleGravity =Gravity.START
            expandedTitleGravity = Gravity.BOTTOM
            titleCollapseMode = CollapsingToolbarLayout.TITLE_COLLAPSE_MODE_SCALE
            expandedTitleMarginStart =16.dp2px()
        }
        initList()
        initView()
    }

    private fun initList() {
        for (i in 1..100) {
            list.add(i.toString() + "")
        }
    }

    private fun initView() {
        val manager = LinearLayoutManager(this).apply { LinearLayoutManager.VERTICAL }
        mBinding.recyclerView.setLayoutManager(manager)
        normalAdapter = AdapterNormal()
        normalAdapter.submitList(list)
        mBinding.recyclerView.setAdapter(normalAdapter)
        initListener()
    }
    private fun initListener() {
        mBinding.toolbar.navigationIcon = R.drawable.navi_back.asDrawable(baseContext)
        mBinding.toolbar.setNavigationNoDoubleClickListener { finish() }
        mBinding.appBar.addOnOffsetChangedListener { appbarLayout, offset ->
            val mHeight: Float = 200.dp2px().toFloat()
            val mOffsetY = abs(offset).toFloat()
            val scale: Float = if (mOffsetY / mHeight > 1) 1.toFloat() else mOffsetY / mHeight
            val alpha: Int = (scale * 255).toInt()
            mBinding.toolbar.setBackgroundColor(Color.argb(alpha, 63, 81, 181))

            val color = if (offset >= appbarLayout.totalScrollRange) {
                ArgbEvaluator().evaluate(scale, Color.argb(255, 34, 22, 19), Color.argb(255, 255, 255, 255)) as Int
            } else {
                ArgbEvaluator().evaluate(1 - scale, Color.argb(255, 255, 255, 255), Color.argb(255, 34, 22, 19)) as Int
            }
            mBinding.toolbar.navigationIcon?.setTint(color)
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ActivityMaterialDesignTopBar::class.java)
            activity.startActivity(intent)
        }
    }
}
