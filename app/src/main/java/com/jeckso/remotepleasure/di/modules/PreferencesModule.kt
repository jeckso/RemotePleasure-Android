package com.jeckso.remotepleasure.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.jeckso.remotepleasure.data.persistence.preferences.proto.user.UserInfo
import com.jeckso.remotepleasure.data.persistence.preferences.proto.user.UserInfoSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    companion object {
        private const val DATAFILE_PATH = "user_preferences.pb"
    }

    @Provides
    @Singleton
    fun authDataStore(
        @ApplicationContext context: Context,
        serializer: UserInfoSerializer
    ): DataStore<UserInfo> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile(DATAFILE_PATH) }
        )
    }
}