package com.ethan.ceiling.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.ceiling.R
import com.ethan.ceiling.adapter.AdapterMallShoppingCategory
import com.ethan.ceiling.adapter.AdapterShopDetail
import com.ethan.ceiling.bean.BeanShopChild
import com.ethan.ceiling.bean.BeanShoppingInfo
import com.ethan.ceiling.databinding.FragmentChildBinding
import com.ethan.ceiling.common.ARG_PARAM1
import com.ethan.ceiling.common.ARG_PARAM2
import com.ethan.ceiling.ext.configGridSpanCount
import com.ethan.ceiling.ext.getAdapterByItemPosition
import com.ethan.flexibleddivider.HorizontalDividerItemDecoration
import com.ethan.flexibleddivider.VerticalDividerItemDecoration
import io.github.uhsk.kit.android.dp2px
import io.github.uhsk.kit.asColor


class FragmentMallShoppingList : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private lateinit var mBinding: FragmentChildBinding

    private val mAdapter = ConcatAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentChildBinding.inflate(inflater, container, false)
        return mBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = initListData()

        list.forEach {
            mAdapter.addAdapter(AdapterMallShoppingCategory().apply {
                add(it.categoryName)
            })
            mAdapter.addAdapter(AdapterShopDetail().apply { addAll(it.list) })
        }
        mBinding.recyclerView.apply {

            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                configGridSpanCount(newSpanCount = 1) {
                    mAdapter.getAdapterByItemPosition(it) !is AdapterShopDetail
                }
                addItemDecoration(
                    HorizontalDividerItemDecoration.Builder(requireContext())
                        .color(R.color.common_background_four.asColor(requireContext()))
                        .showLastDivider()
                        .size(10.dp2px())
                        .build()
                )
                addItemDecoration(
                    VerticalDividerItemDecoration.Builder(requireContext())
                        .color(R.color.common_background_four.asColor(requireContext()))
                        .showLastDivider()
                        .size(10.dp2px())
                        .build()
                )
                adapter = mAdapter
            }
        }
    }

    private fun initListData(): MutableList<BeanShoppingInfo> {
        val list: MutableList<BeanShoppingInfo> = mutableListOf()
        for (i in 0..3) {
            val childList = mutableListOf<BeanShopChild>()
            for (j in 0..20) {
                val beanShopChild = BeanShopChild("https://p3.dcarimg.com/img/tos-cn-i-dcdx/c194cffd1d394617a1aaf2a853316759~1200x0.png", "这是分类$i 的商品$j", 1000, "商品详情$j")
                childList.add(beanShopChild)
            }
            val beanShoppingInfo = BeanShoppingInfo(categoryName = "这是一个分类名$i,这里输入长字符串,用于测试Span", list = childList)
            list.add(beanShoppingInfo)
        }
        return list
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String?, param2: String?): FragmentMallShoppingList {
            val fragment = FragmentMallShoppingList()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.setArguments(args)
            return fragment
        }
    }
}