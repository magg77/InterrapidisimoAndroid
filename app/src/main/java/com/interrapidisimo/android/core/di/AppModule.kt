package com.interrapidisimo.android.core.di

import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceAuthRemoteImpl
import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceAuthRemote
import com.interrapidisimo.android.auth.data.repository.remote.AuthenticateRemoteRepository
import com.interrapidisimo.android.auth.data.repository.remote.AuthenticateRemoteRepositoryImpl
import com.interrapidisimo.android.auth.domain.remote.AuthenticateRemoteUseCaseImpl
import com.interrapidisimo.android.auth.domain.remote.AuthenticateRemoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun authenticateRemoteUseCase(userCase: AuthenticateRemoteUseCaseImpl): AuthenticateRemoteUseCase

    @Binds
    abstract fun authenticateRemoteRepository(repo: AuthenticateRemoteRepositoryImpl): AuthenticateRemoteRepository

    @Binds
    abstract fun dataSourceAuthRemote(dataRemote: DataSourceAuthRemoteImpl): DataSourceAuthRemote

}