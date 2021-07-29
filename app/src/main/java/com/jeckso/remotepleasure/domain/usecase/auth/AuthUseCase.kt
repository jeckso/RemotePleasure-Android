package com.jeckso.remotepleasure.domain.usecase.auth

import com.jeckso.remotepleasure.data.network.rest.model.auth.AuthResponse
import com.jeckso.remotepleasure.data.network.rest.model.auth.BaseAuthRequest
import com.jeckso.remotepleasure.data.network.rest.services.AuthService
import com.jeckso.remotepleasure.data.network.rest.services.UserService
import com.jeckso.remotepleasure.data.persistence.preferences.proto.user.UserInfoPreferencesManager
import com.jeckso.remotepleasure.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val userService: UserService,
    private val userInfoPreferencesManager: UserInfoPreferencesManager
) : BaseUseCase<String, AuthResponse>() {

    override suspend fun execute(vararg input: String): AuthResponse {
        val (credentials) = input
        val baseAuthRequest = BaseAuthRequest(credentials)
        val result = userService.createUser(baseAuthRequest)
        userInfoPreferencesManager.updateData {
            it.toBuilder()
                .setName(result.name)
                .setId(result.id)
                .build()
        }
        return result
    }

}