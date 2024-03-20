package com.ethan.ceiling.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ethan.ceiling.R


class NormalAdapter(var datas: List<String>?) : RecyclerView.Adapter<NormalAdapter.ViewHolder>() {
    private var mHeaderView: View? = null
    private var mFooterView: View? = null
    fun setmHeaderView(mHeaderView: View?) {
        this.mHeaderView = mHeaderView
    }

    fun setmFooterView(mFooterView: View?) {
        this.mFooterView = mFooterView
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    override fun getItemViewType(position: Int): Int {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL
        }
        if (position == 0) {
            return if (mHeaderView != null) {
                //第一个item应该加载Header
                TYPE_HEADER
            } else {
                TYPE_NORMAL
            }
        }
        return if (position == itemCount - 1) {
            if (mFooterView != null) {
                //最后一个,应该加载Footer
                TYPE_FOOTER
            } else {
                TYPE_NORMAL
            }
        } else TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType) {
            TYPE_HEADER -> mHeaderView
            TYPE_FOOTER -> mFooterView
            TYPE_NORMAL -> LayoutInflater.from(parent.context).inflate(R.layout.item_mall_shop_detail, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_mall_shop_detail, parent, false)
        }
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (mHeaderView != null) {
                holder.mTextView.text = datas!![position - 1]
            } else {
                holder.mTextView.text = datas!![position]
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mHeaderView == null && mFooterView == null) {
            if (datas == null) 0 else datas!!.size
        } else if (mHeaderView == null) {
            if (datas == null) 0 else datas!!.size + 1
        } else if (mFooterView == null) {
            if (datas == null) 0 else datas!!.size + 1
        } else {
            if (datas == null) 0 else datas!!.size + 2
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            when {
                itemView === mHeaderView -> {

                }
                itemView === mFooterView -> {

                }
            }
            mTextView = itemView.findViewById(R.id.tv_shop_name)
        }
    }

    val isHasHeader: Boolean
        //是否是头部布局
        get() = mHeaderView != null

    //是否是固定的顶部布局
    fun isFirstItem(position: Int): Boolean {
        return if (mHeaderView != null && position == 1) {
            true
        } else mHeaderView == null && position == 0
    }

    companion object {
        const val TYPE_HEADER = 0 //说明是带有Header的
        const val TYPE_FOOTER = 1 //说明是带有Footer的
        const val TYPE_NORMAL = 2 //说明是不带有header和footer的
    }
}
