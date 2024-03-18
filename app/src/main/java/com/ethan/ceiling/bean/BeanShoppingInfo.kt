package com.ethan.ceiling.bean

data class BeanShoppingInfo(var categoryName: String, var list: MutableList<BeanShopChild> = arrayListOf())