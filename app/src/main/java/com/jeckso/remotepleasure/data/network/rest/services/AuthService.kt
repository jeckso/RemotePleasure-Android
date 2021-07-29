package com.jeckso.remotepleasure.data.network.rest.services

import com.jeckso.remotepleasure.data.network.rest.model.auth.AuthResponse
import com.jeckso.remotepleasure.data.network.rest.model.auth.BaseAuthRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService : BaseService {

    companion object {
        private const val AUTHORIZATION_ENDPOINT = "token"
        private const val REGISTRATION_ENDPOINT = "/users/"
        private const val VERIFICATION_ENDPOINT = "users/phone-verification"
        private const val CONTENT_TYPE = "Content-Type: application/merge-patch+json"
    }

    @POST(REGISTRATION_ENDPOINT)
    suspend fun authorize(@Body baseAuthRequest: BaseAuthRequest): AuthResponse

}