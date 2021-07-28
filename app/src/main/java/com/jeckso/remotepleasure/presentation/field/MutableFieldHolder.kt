package com.jeckso.remotepleasure.presentation.field

interface MutableFieldHolder<T : Any> : FieldHolder<T> {

    fun setValue(value: T?)
}