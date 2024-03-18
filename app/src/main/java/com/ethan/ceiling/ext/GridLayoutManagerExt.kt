package com.ethan.ceiling.ext

import androidx.recyclerview.widget.GridLayoutManager

fun GridLayoutManager.configGridSpanCount(newSpanCount: Int, range: (position: Int) -> Boolean) {
    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (range(position)) spanCount else newSpanCount
        }
    }
}