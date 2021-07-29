package com.jeckso.remotepleasure.data.persistence.preferences.proto

import androidx.datastore.core.DataStore

open class BasePreferencesManager<T>(
    protected val preferences: DataStore<T>
) : DataStore<T> by preferences