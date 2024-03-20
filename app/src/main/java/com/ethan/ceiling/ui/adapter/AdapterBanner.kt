package com.ethan.ceiling.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethan.ceiling.R
import com.ethan.ceiling.bean.BeanBanner
import com.ethan.ceiling.databinding.LayoutBannerItemBinding
import com.youth.banner.adapter.BannerAdapter

class AdapterBanner(list: ArrayList<BeanBanner>) : BannerAdapter<BeanBanner, AdapterBanner.BannerViewHolder>(list) {

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mBinding: LayoutBannerItemBinding = LayoutBannerItemBinding.bind(view)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.layout_banner_item, parent, false)
        return BannerViewHolder(rootView)
    }

    override fun onBindView(holder: BannerViewHolder, item: BeanBanner, position: Int, size: Int) {
        Glide.with(holder.mBinding.root.context).load(item.imgUrl).into(holder.mBinding.picture)
    }
}