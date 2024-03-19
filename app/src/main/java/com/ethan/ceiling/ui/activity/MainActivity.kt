package com.ethan.ceiling.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ethan.ceiling.databinding.ActivityMainBinding
import com.ethan.ceiling.manager.IRuntimeManager
import com.ethan.ceiling.ui.activity.ActivityCeilingDouble.Companion.start
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var mBinding: ActivityMainBinding
    private val mRuntimeManager: IRuntimeManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBinding.layoutParent.apply {
            layoutParams.apply {
                setPadding(0, mRuntimeManager.statusBarHeight, 0, 0)
            }
        }
        setContentView(mBinding.getRoot())
    }



    fun onClickOneTopBar(view: View) {
        OneTopBarActivity.start(this)
    }

    fun onClickListView(view: View) {

    }

    fun onClickMaterialDesign(view: View) {
        MaterialDesignTopBarActivity.start(this)
    }

    fun onClickDecoration(view: View) {
        DecorationActivity.start(this)
    }

    fun onClickGroupAndDecoration(view: View) {
        GroupAndDecorationActivity.start(view.context)
    }



    fun onComplexCeilings(view: View) {
        start(view.context)
    }
}
