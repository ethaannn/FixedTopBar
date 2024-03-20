package com.ethan.ceiling.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ethan.ceiling.databinding.ActivityMainBinding
import com.ethan.ceiling.manager.IRuntimeManager
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var mBinding: ActivityMainBinding
    private val mRuntimeManager: IRuntimeManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())
        initToolbar()
    }

    private fun initToolbar() {
        mBinding.toolbar.apply {
            layoutParams.apply {
                setPadding(0, mRuntimeManager.statusBarHeight, 0, 0)
            }
        }
        mBinding.toolbar.title = "吸顶效果合集"
        mBinding.toolbar.isTitleCentered = true
        setSupportActionBar(mBinding.toolbar)
    }

    fun onComplexCeilings(view: View) {
        ActivityCeilingDouble.start(view.context)
    }

    fun onClickMaterialDesign(view: View) {
        ActivityMaterialDesignTopBar.start(this)
    }

    fun onClickDecoration(view: View) {
        DecorationActivity.start(this)
    }

    fun onClickGroupAndDecoration(view: View) {
        GroupAndDecorationActivity.start(view.context)
    }


}
