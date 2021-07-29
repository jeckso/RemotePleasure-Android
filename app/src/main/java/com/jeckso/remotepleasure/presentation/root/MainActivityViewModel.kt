package com.jeckso.remotepleasure.presentation.root

import com.jeckso.remotepleasure.presentation.root.navigation.LoginState
import com.jeckso.remotepleasure.presentation.root.navigation.MainState
import com.jeckso.remotepleasure.data.persistence.preferences.proto.user.UserInfoPreferencesManager
import com.jeckso.remotepleasure.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userInfoPreferencesManager: UserInfoPreferencesManager,
) : BaseViewModel() {

    companion object {
        private const val TIMEOUT = 3000L
    }

    fun checkCurrentSession() = proceedUi {
        val data = try {
            withContext(Dispatchers.IO) {
                withTimeout(TIMEOUT) {
                    userInfoPreferencesManager.data.first()
                }
            }
        } catch (ex: Exception) {
            null
        }
        val state = when {

            data?.id.isNullOrBlank() -> {
                LoginState
            }
            else -> {
                MainState
            }
        }
        Timber.e("STATE $state")
        _navigationState.tryEmit(state)
    }
}