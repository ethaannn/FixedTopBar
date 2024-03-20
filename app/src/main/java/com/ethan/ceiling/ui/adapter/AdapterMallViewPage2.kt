package com.ethan.ceiling.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ethan.ceiling.ui.fragment.FragmentMallShoppingList.Companion.newInstance

class AdapterMallViewPage2(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val list: List<String>) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return newInstance(list[position], "test")
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
