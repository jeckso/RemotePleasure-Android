package com.jeckso.remotepleasure.domain.validation.base

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.function.Predicate

class BaseValidator<T>(private val predicate: Predicate<T>) : Validator<T> {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun validate(input: T) {
        if (!predicate.test(input)) throw IllegalArgumentException()
    }
}