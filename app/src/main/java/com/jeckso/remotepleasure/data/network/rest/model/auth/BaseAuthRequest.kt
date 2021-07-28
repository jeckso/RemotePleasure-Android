package com.jeckso.remotepleasure.data.network.rest.model.auth

import com.google.gson.annotations.SerializedName

open class BaseAuthRequest constructor(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: String
)