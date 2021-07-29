package com.jeckso.remotepleasure.presentation.implementation.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.jeckso.remotepleasure.R
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment
import com.jeckso.remotepleasure.presentation.base.vm.BaseViewModel

abstract class BaseRootFragment<VM: BaseViewModel, VB: ViewBinding> : BaseFragment<VM, VB>(){

    fun replace(tag: String, fragment: Fragment, addToBackStack: Boolean = true) = childFragmentManager.commit {
        replace(R.id.fragmentContainer, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}