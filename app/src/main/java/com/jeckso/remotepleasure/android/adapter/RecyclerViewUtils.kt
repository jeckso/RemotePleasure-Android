package com.jeckso.remotepleasure.android.adapter

fun interface OnItemClickListener<T> {

    fun onItemClick(item: T, position: Int)

}