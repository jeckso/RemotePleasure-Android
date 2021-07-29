package com.jeckso.remotepleasure.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeckso.remotepleasure.databinding.FragmentAuthorizationBinding
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment

class LoginFragment private constructor() :
    BaseFragment<LoginViewModel, FragmentAuthorizationBinding>() {

    companion object {
        const val TAG = "LoginFragment"

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override val viewModel: LoginViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentAuthorizationBinding {
        return FragmentAuthorizationBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            loginButton.setOnClickListener {
                viewModel.authorize(
                    viewBinding.nameEditText.text.toString()
                )
            }
        }
    }
}