package com.jeckso.remotepleasure.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.jeckso.remotepleasure.R
import com.jeckso.remotepleasure.databinding.ActivityWithFragmentBinding
import com.jeckso.remotepleasure.presentation.base.activity.BaseActivity
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment
import com.jeckso.remotepleasure.presentation.users.UsersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityWithFragmentBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun inflateViewBinding(savedInstanceState: Bundle?): ActivityWithFragmentBinding {
        return ActivityWithFragmentBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replace(UsersListFragment.newInstance(), UsersListFragment.TAG, addToBackStack = false)
    }

    fun replace(
        fragment: BaseFragment<*, *>,
        tag: String? = null,
        enterAnimation: Int = R.anim.anim_move_to_top,
        exitAnimation: Int = R.anim.anim_move_to_bottom,
        addToBackStack: Boolean = true
    ) = supportFragmentManager.commit {
        setCustomAnimations(enterAnimation, exitAnimation, enterAnimation, exitAnimation)
        replace(R.id.fragment_container, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }

}