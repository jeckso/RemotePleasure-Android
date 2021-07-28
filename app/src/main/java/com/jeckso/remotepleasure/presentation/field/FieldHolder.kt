package com.jeckso.remotepleasure.presentation.field

import kotlinx.coroutines.flow.Flow

interface FieldHolder<T> {

    val state: Flow<Result<T>>
}