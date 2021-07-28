package com.jeckso.remotepleasure.presentation.base.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckso.remotepleasure.presentation.field.MutableFieldHolderImpl
import com.jeckso.remotepleasure.presentation.utils.BackState
import com.jeckso.remotepleasure.presentation.utils.Failure
import com.jeckso.remotepleasure.presentation.utils.NavigationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    private val _activeTasks = mutableSetOf<String>()

    private val _activeTaskMutex = Mutex()

    protected val _navigationState: MutableSharedFlow<NavigationState> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    protected val _errorState: MutableSharedFlow<Failure> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    protected val _shouldShowProgress: MutableSharedFlow<Boolean> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val navigationState: Flow<NavigationState> = _navigationState

    val errorState: Flow<Failure> = _errorState

    val shouldShowProgress: Flow<Boolean> = _shouldShowProgress.transform {
        emit(it or _activeTasks.isNotEmpty())
    }.distinctUntilChanged()

    open fun navigateBack() {
        _navigationState.tryEmit(BackState())
    }

    fun proceedUi(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception) {
                Timber.e(ex)
            }
        }

    suspend fun <T> progressive(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend () -> T
    ): T {
        val taskId = createActiveTask()
        return try {
            _shouldShowProgress.emit(true)
            withContext(context) { block() }
        } finally {
            removeActiveTask(taskId)
            _shouldShowProgress.emit(false)
        }
    }

    suspend fun <T> proceed(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend () -> T
    ): T? {
        val taskId = createActiveTask()
        return try {
            val result = withContext(context) { block() }
            _shouldShowProgress.emit(false)
            result
        } catch (ex: Exception) {
            Timber.e(ex)
            _errorState.tryEmit(Failure(error = ex))
            null
        } finally {
            removeActiveTask(taskId)
            _shouldShowProgress.emit(false)
        }
    }

    protected val <Value : Any> MutableFieldHolderImpl<Value>.value: Value?
        get() = (valueHolder as? MutableStateFlow<Value?>)?.value


    protected fun handleException(javaClassString: String?, message: String?, httpCode: Int) {
        val exception = when (javaClassString) {
            ConnectException::class.java.canonicalName -> ConnectException()
            SocketException::class.java.canonicalName -> SocketException()
            UnknownHostException::class.java.canonicalName -> UnknownHostException()
            else -> Exception(message)
        }
        _errorState.tryEmit(Failure(error = exception))
    }

    private suspend fun addActiveTask(id: String) = _activeTaskMutex.withLock(this) {
        _activeTasks.add(id)
    }

    protected suspend fun createActiveTask(): String {
        return UUID.randomUUID().toString().also { addActiveTask(it) }
    }

    protected suspend fun removeActiveTask(id: String) = _activeTaskMutex.withLock(this) {
        _activeTasks.remove(id)
    }

    protected fun <T> Flow<T>.progressive(taskId: String = UUID.randomUUID().toString()) = onStart {
        addActiveTask(taskId)
        _shouldShowProgress.emit(true)
    }.catch {
        _errorState.emit(Failure(error = it))
    }.onCompletion {
        removeActiveTask(taskId)
        _shouldShowProgress.emit(false)
    }

}