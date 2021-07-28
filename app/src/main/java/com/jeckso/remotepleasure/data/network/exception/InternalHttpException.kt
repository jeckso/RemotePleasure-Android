package com.jeckso.remotepleasure.data.network.exception

class InternalHttpException(
    val code: Int,
    override val message: String?
) : Exception()