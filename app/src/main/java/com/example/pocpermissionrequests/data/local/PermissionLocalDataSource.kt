package com.example.pocpermissionrequests.data.local

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PermissionLocalDataSource {
    fun checkPermission(permission: String): Flow<Boolean>
}

class PermissionLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): PermissionLocalDataSource {
    override fun checkPermission(permission: String) = flow {
        while (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            context.checkSelfPermission(
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            emit(false)
            delay(1000)
        }
        emit(true)
    }
}
