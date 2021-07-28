package com.jeckso.remotepleasure.data.network.utils

import com.jeckso.remotepleasure.data.network.exception.InternalHttpException
import retrofit2.HttpException

val InternalHttpException.isUnprocessableEntity
    get() = code == 422

val HttpException.isBadRequest
    get() = code() == 400