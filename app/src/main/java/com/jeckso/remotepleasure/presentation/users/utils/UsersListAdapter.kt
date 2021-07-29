package com.jeckso.remotepleasure.presentation.users.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jeckso.remotepleasure.android.adapter.BaseRecyclerViewAdapter
import com.jeckso.remotepleasure.android.adapter.OnItemClickListener
import com.jeckso.remotepleasure.databinding.ItemUserBinding

class UsersListAdapter(listener: OnItemClickListener<UserVM>): BaseRecyclerViewAdapter<UserVM, UserVH>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserVH(binding)
    }
}