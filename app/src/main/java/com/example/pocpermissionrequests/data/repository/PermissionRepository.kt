package com.example.pocpermissionrequests.data.repository

import android.Manifest
import com.example.pocpermissionrequests.data.local.PermissionLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PermissionRepository {
    fun getPermission(location: String): Flow<Boolean>
}

class PermissionRepositoryImpl @Inject constructor(
    private val permissionLocalDataSource: PermissionLocalDataSource,
): PermissionRepository {
    override fun getPermission(location: String) =
        permissionLocalDataSource.checkPermission(location)

}
