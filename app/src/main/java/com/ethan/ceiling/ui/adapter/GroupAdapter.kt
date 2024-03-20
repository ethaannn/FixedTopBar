package com.ethan.ceiling.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ethan.ceiling.R
import com.ethan.ceiling.bean.TestData

class GroupAdapter(private val datas: List<TestData>?) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context) .inflate(R.layout.item_mall_shop_detail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val text = datas!![i].text
        viewHolder.mTextView.text = text
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.tv_shop_name)
        }
    }

    /**
     * 获取position对应的Item组名
     *
     * @param position
     * @return
     */
    fun getGroupName(position: Int): String {
        return datas!![position].groupName
    }

    /**
     * 判断position对应的Item是否是组的第一项
     *
     * @param position
     * @return
     */
    fun isItemHeader(position: Int): Boolean {
        return if (position == 0) {
            true
        } else {
            val lastGroupName = datas!![position - 1].groupName
            val currentGroupName = datas[position].groupName
            //判断上一个数据的组别和下一个数据的组别是否一致，如果不一致则是不同组，也就是为第一项（头部）
            lastGroupName != currentGroupName
        }
    }
}
