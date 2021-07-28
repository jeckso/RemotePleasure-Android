package com.jeckso.remotepleasure.domain.usecase.auth

import com.jeckso.remotepleasure.data.network.rest.model.auth.AuthResponse
import com.jeckso.remotepleasure.data.network.rest.model.auth.BaseAuthRequest
import com.jeckso.remotepleasure.data.network.rest.services.AuthService
import com.jeckso.remotepleasure.data.persistence.preferences.proto.user.UserInfoPreferencesManager
import com.jeckso.remotepleasure.domain.usecase.base.BaseUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val authService: AuthService,
    private val userInfoPreferencesManager: UserInfoPreferencesManager
) : BaseUseCase<Pair<String, String>, AuthResponse>() {

    override suspend fun execute(vararg input: Pair<String, String>): AuthResponse {
        val (credentials) = input
        val passwordAuthRequest = BaseAuthRequest(credentials.first, credentials.second)
        val result = authService.authorize(passwordAuthRequest)
        userInfoPreferencesManager.updateData {
            it.toBuilder()
                .setName(result.name)
                .setId(result.id)
                .build()
        }
        return result
    }

}