package com.jeckso.remotepleasure.presentation.root

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.jeckso.remotepleasure.R
import com.jeckso.remotepleasure.databinding.FragmentRootBinding
import com.jeckso.remotepleasure.presentation.auth.LoginFragment
import com.jeckso.remotepleasure.presentation.base.activity.BaseActivity
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment
import com.jeckso.remotepleasure.presentation.root.navigation.LoginState
import com.jeckso.remotepleasure.presentation.root.navigation.MainState
import com.jeckso.remotepleasure.presentation.utils.NextScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, FragmentRootBinding>() {

    override val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkCurrentSession()
    }

    override fun inflateViewBinding(savedInstanceState: Bundle?): FragmentRootBinding {
        return FragmentRootBinding.inflate(LayoutInflater.from(this))
    }

    override fun handleCustomNavigation(state: NextScreenState) {
        when (state) {
            LoginState -> {
                replace(
                    LoginFragment.newInstance(),
                    tag = LoginFragment.TAG,
                    addToBackStack = false
                )
            }
            MainState -> {
            }
            else -> super.handleCustomNavigation(state)
        }
    }

    fun replace(
        fragment: BaseFragment<*, *>,
        tag: String? = null,
//        enterAnimation: Int = R.anim.anim_move_to_top,
//        exitAnimation: Int = R.anim.anim_move_to_bottom,
        addToBackStack: Boolean = true
    ) = supportFragmentManager.commit {
       // setCustomAnimations(enterAnimation, exitAnimation, enterAnimation, exitAnimation)
        replace(R.id.fragmentContainer, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}