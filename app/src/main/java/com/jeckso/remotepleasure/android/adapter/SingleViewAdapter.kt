package com.jeckso.remotepleasure.android.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class SingleViewAdapter<T, VH : BaseViewHolder<T, *>>(
    _listener: OnItemClickListener<T>?
) : RecyclerView.Adapter<VH>() {

    var listener: OnItemClickListener<T>? = _listener
        private set


    var item: T? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(item!!, listener)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int {
        return when (item) {
            null -> 0
            else -> 1
        }
    }
}
