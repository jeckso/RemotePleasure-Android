package com.jeckso.remotepleasure.data.network.rest.services

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jeckso.remotepleasure.data.network.rest.services.BaseService.Companion.KEY_ID
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService: BaseService {

    companion object {
        private const val ENDPOINT = "/users"
    }

    @POST(ENDPOINT)
    suspend fun createUser(user: JsonObject): JsonObject

    @GET(ENDPOINT)
    suspend fun getUsers(): JsonArray

    @GET("$ENDPOINT/${KEY_ID}")
    suspend fun getUserById(@Path(KEY_ID) id: String): JsonObject

    @DELETE("$ENDPOINT/${KEY_ID}")
    suspend fun deleteUser(@Path(KEY_ID) id: String): JsonObject

}