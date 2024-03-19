package com.ethan.ceiling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ethan.ceiling.R
import com.ethan.ceiling.databinding.ItemNormal1Binding

class AdapterNormal : BaseQuickAdapter<String, AdapterNormal.NormalViewHolder>() {


    inner class NormalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mBinding: ItemNormal1Binding = ItemNormal1Binding.bind(view)
            private set
    }

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int, item: String?) {
       holder.mBinding.item.text =item?:""
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): NormalViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_normal_1, parent, false)
        return NormalViewHolder(view)
    }
}