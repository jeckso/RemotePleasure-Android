package com.jeckso.remotepleasure.data.persistence.preferences.proto.user

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoSerializer @Inject constructor() : Serializer<UserInfo> {

    override val defaultValue: UserInfo
        get() = UserInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserInfo {
        return UserInfo.parseFrom(input)
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        return t.writeTo(output)
    }
}