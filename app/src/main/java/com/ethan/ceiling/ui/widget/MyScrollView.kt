package com.ethan.ceiling.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.common.eventbus.EventBus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("UnstableApiUsage")
class MyScrollView : NestedScrollView, KoinComponent,DefaultLifecycleObserver {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    private val mGuavaEventBus:EventBus by inject()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mGuavaEventBus.register(this)
    }
    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        mGuavaEventBus.post(ScrollChangedEvent(l, t, oldl, oldt))
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        mGuavaEventBus.unregister(this)
    }


    data class ScrollChangedEvent(val x: Int, val y: Int, val oldX: Int, val oldY: Int)
}
