package com.ethan.ceiling.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethan.ceiling.ui.adapter.GroupAdapter
import io.github.uhsk.kit.android.dp2px
import kotlin.math.min


class GroupDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mItemHeaderHeight: Int = 40.dp2px()
    private val mTextPaddingLeft: Int = 6.dp2px()

    //画笔，绘制头部和分割线
    private val mItemHeaderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mLinePaint: Paint =Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextRect: Rect = Rect()

    init {
        mItemHeaderPaint.setColor(Color.BLUE)
        mTextPaint.textSize = 46f
        mTextPaint.setColor(Color.WHITE)
        mLinePaint.setColor(Color.GRAY)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter is GroupAdapter) {
            //如果是每组第一项我们空出头部布局的高度，如果不是，我们则空出分割线的高度
            val adapter = parent.adapter as GroupAdapter?
            val position = parent.getChildLayoutPosition(view)
            val isHeader = adapter!!.isItemHeader(position)
            if (isHeader) {
                outRect.top = mItemHeaderHeight
            } else {
                outRect.top = 1
            }
        }
    }

    //绘制item的分割线和组头
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter is GroupAdapter) {
            val adapter = parent.adapter as GroupAdapter
            val count = parent.childCount
            for (i in 0 until count) {
                val view = parent.getChildAt(i)
                val position = parent.getChildLayoutPosition(view)
                val isHeader = adapter.isItemHeader(position)
                if (isHeader) {
                    c.drawRect(0f, (view.top - mItemHeaderHeight).toFloat(), parent.width.toFloat(), view.top.toFloat(), mItemHeaderPaint)
                    mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length, mTextRect)
                    c.drawText(adapter.getGroupName(position), mTextPaddingLeft.toFloat(), (view.top - mItemHeaderHeight + mItemHeaderHeight / 2 + mTextRect.height() / 2).toFloat(), mTextPaint)
                } else {
                    c.drawRect(0f, (view.top - 1).toFloat(), parent.width.toFloat(), view.top.toFloat(), mLinePaint)
                }
            }
        }
    }

    /**
     * 吸顶效果的主要实现方法
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter is GroupAdapter) {
            val adapter = parent.adapter as GroupAdapter
            val position = (parent.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            val view = parent.findViewHolderForAdapterPosition(position)!!.itemView
            val isHeader = adapter.isItemHeader(position + 1)
            if (isHeader) {
                val bottom = min(mItemHeaderHeight.toDouble(), view.bottom.toDouble()).toInt()
                c.drawRect(0f, (view.top - mItemHeaderHeight).toFloat(), parent.width.toFloat(), bottom.toFloat(), mItemHeaderPaint)
                mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length, mTextRect)
                c.drawText(adapter.getGroupName(position), mTextPaddingLeft.toFloat(), (mItemHeaderHeight / 2 + mTextRect.height() / 2 - (mItemHeaderHeight - bottom)).toFloat(), mTextPaint)
            } else {
                c.drawRect(0f, 0f, parent.width.toFloat(), mItemHeaderHeight.toFloat(), mItemHeaderPaint)
                mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length, mTextRect)
                c.drawText(adapter.getGroupName(position), mTextPaddingLeft.toFloat(), (mItemHeaderHeight / 2 + mTextRect.height() / 2).toFloat(), mTextPaint)
            }
        }
    }
}
