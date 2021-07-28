package com.jeckso.remotepleasure.presentation.users.utils

import com.jeckso.remotepleasure.android.adapter.BaseViewHolder
import com.jeckso.remotepleasure.android.adapter.OnItemClickListener
import com.jeckso.remotepleasure.databinding.ItemUserBinding

class UserVH(binding: ItemUserBinding) : BaseViewHolder<UserVM, ItemUserBinding>(binding) {

    override fun bind(item: UserVM, listener: OnItemClickListener<UserVM>?) {
        super.bind(item, listener)
        binding.root.text = item.name
    }
}