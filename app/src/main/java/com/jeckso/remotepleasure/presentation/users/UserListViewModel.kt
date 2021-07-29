package com.jeckso.remotepleasure.presentation.users

import com.jeckso.remotepleasure.data.network.rest.services.UserService
import com.jeckso.remotepleasure.presentation.base.vm.BaseViewModel
import com.jeckso.remotepleasure.presentation.users.utils.UserVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userService: UserService
): BaseViewModel() {

    val users: Flow<List<UserVM>> = flow {
        emit(userService.getUsers())
    }.map { list ->
        list.map { UserVM("dsada", "1231341") }
    }.progressive()

    fun connect(userService: UserVM) {

    }

    fun refresh() {

    }
}