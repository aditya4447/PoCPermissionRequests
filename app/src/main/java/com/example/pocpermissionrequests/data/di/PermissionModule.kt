package com.example.pocpermissionrequests.data.di

import com.example.pocpermissionrequests.data.local.PermissionLocalDataSource
import com.example.pocpermissionrequests.data.local.PermissionLocalDataSourceImpl
import com.example.pocpermissionrequests.data.repository.PermissionRepository
import com.example.pocpermissionrequests.data.repository.PermissionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {
    @Binds
    abstract fun bindPermissionLocalDataSource(
        permissionLocalDataSourceImpl: PermissionLocalDataSourceImpl,
    ): PermissionLocalDataSource

    @Binds
    abstract fun bindPermissionRepository(
        permissionRepositoryImpl: PermissionRepositoryImpl,
    ): PermissionRepository
}
