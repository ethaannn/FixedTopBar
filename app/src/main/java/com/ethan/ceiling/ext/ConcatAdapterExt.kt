package com.ethan.ceiling.ext

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

fun ConcatAdapter.getAdapterByItemPosition(position: Int): RecyclerView.Adapter<*>? {
    var tempPosition = position
    adapters.forEach {
        when {
            tempPosition >= it.itemCount -> tempPosition -= it.itemCount
            tempPosition < 0             -> return null
            else                         -> return it
        }
    }
    return null
}