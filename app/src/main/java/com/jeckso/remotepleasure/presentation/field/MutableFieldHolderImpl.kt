package com.jeckso.remotepleasure.presentation.field

import com.jeckso.remotepleasure.domain.validation.base.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MutableFieldHolderImpl<T : Any>(
    initial: T? = null,
    validators: List<Validator<T>> = emptyList(),
) : MutableFieldHolder<T> {

    private val _valueHolder: MutableStateFlow<T?> = MutableStateFlow(initial)

    override fun setValue(value: T?) {
        _valueHolder.value = value
    }

    val valueHolder: Flow<T?> = _valueHolder

    override val state: Flow<Result<T>> = _valueHolder
        .filterNotNull()
        .onEach { value -> validators.forEach { it.validate(value) } }
        .flowOn(Dispatchers.Default)
        .map { Result.success(it) }
        .catch { Result.failure<T>(it) }
}