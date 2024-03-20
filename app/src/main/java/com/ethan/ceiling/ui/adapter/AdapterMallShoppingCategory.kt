package com.ethan.ceiling.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.ethan.ceiling.R
import com.ethan.ceiling.databinding.LayoutMallShoppingCategoryItemBinding
import com.zhpan.bannerview.BaseViewHolder

class AdapterMallShoppingCategory : BaseQuickAdapter<String, QuickViewHolder>() {

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
        item?.let {
            val binding = LayoutMallShoppingCategoryItemBinding.bind(holder.itemView)
            binding.tvCategoryName.text = it
        }
    }


    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): QuickViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_mall_shopping_category_item, parent, false)
        return QuickViewHolder(rootView)
    }

    override fun isFullSpanItem(itemType: Int): Boolean {
        return true
    }

}