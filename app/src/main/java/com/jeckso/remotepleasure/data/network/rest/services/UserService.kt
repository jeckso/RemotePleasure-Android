package com.jeckso.remotepleasure.data.network.rest.services

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jeckso.remotepleasure.data.network.rest.model.auth.AuthResponse
import com.jeckso.remotepleasure.data.network.rest.model.auth.BaseAuthRequest
import com.jeckso.remotepleasure.data.network.rest.services.BaseService.Companion.KEY_ID
import retrofit2.http.*

interface UserService: BaseService {

    companion object {
        private const val ENDPOINT = "/users"
    }

    @POST(ENDPOINT)
    suspend fun createUser(@Body baseAuthRequest: BaseAuthRequest): AuthResponse

    @GET(ENDPOINT)
    suspend fun getUsers(): JsonArray

    @GET("$ENDPOINT/${KEY_ID}")
    suspend fun getUserById(@Path(KEY_ID) id: String): JsonObject

    @DELETE("$ENDPOINT/${KEY_ID}")
    suspend fun deleteUser(@Path(KEY_ID) id: String): JsonObject

}