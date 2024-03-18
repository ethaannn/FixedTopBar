package com.ethan.ceiling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.ethan.ceiling.R
import com.ethan.ceiling.bean.BeanShopChild
import com.ethan.ceiling.databinding.ItemMallShopDetailBinding
import com.ethan.ceiling.databinding.LayoutMallShoppingCategoryItemBinding
import com.zhpan.bannerview.BaseViewHolder

class AdapterShopDetail : BaseQuickAdapter<BeanShopChild, AdapterShopDetail.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemMallShopDetailBinding = ItemMallShopDetailBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, item: BeanShopChild?) {
        item?.let {
            holder.binding.tvShopDetail.text = it.detail
            holder.binding.tvShopName.text = it.title
            Glide.with(holder.itemView.context).load(it.imgUrl).into(holder.binding.ivShopCover)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_mall_shop_detail, parent, false)
        return MyViewHolder(rootView)
    }
}

