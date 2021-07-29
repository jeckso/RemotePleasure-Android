package com.jeckso.remotepleasure.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeckso.remotepleasure.R
import com.jeckso.remotepleasure.data.network.utils.isBadRequest
import com.jeckso.remotepleasure.databinding.FragmentAuthorizationBinding
import com.jeckso.remotepleasure.presentation.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import timber.log.Timber
@AndroidEntryPoint
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
        Timber.e("CREATED")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("CREATED VIEW")
        with(viewBinding) {
            loginButton.setOnClickListener {
                viewModel.authorize(
                    viewBinding.nameEditText.text.toString()
                )
            }
        }
    }
    override fun handleError(error: Throwable) {
        when (error) {
            is HttpException -> {
                if (error.isBadRequest) {
                    showErrorDialog(getString(R.string.title_error))
                } else {
                    super.handleError(error)
                }
            }
            else -> super.handleError(error)
        }
    }
}