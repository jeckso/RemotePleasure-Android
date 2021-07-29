package com.jeckso.remotepleasure.data.persistence.preferences.proto.user

import androidx.datastore.core.DataStore
import com.jeckso.remotepleasure.data.persistence.preferences.proto.BasePreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoPreferencesManager @Inject constructor(
    dataStore: DataStore<UserInfo>
) : BasePreferencesManager<UserInfo>(dataStore)