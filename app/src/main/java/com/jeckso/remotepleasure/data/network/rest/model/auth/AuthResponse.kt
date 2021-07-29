package com.jeckso.remotepleasure.data.network.rest.model.auth

import com.google.gson.annotations.SerializedName


data class AuthResponse constructor(
    @SerializedName("name")
    val name : String,
    @SerializedName("id")
    val id : String
)
