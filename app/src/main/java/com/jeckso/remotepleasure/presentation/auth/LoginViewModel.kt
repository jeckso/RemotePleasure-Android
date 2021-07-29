package com.jeckso.remotepleasure.presentation.auth

import com.jeckso.remotepleasure.domain.usecase.auth.AuthUseCase
import com.jeckso.remotepleasure.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    fun authorize(login: String) = proceedUi {
        val result = proceed(Dispatchers.IO) {
            authUseCase.execute(login)
        }
        //result?.let { _navigationState.tryEmit(MainNavigationState) }
    }
}