package com.jeckso.remotepleasure.domain.usecase.base

abstract class BaseUseCase<Input, Output> {

    abstract suspend fun execute(vararg input: Input): Output
}