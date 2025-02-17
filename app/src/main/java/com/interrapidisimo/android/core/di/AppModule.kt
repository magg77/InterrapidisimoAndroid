package com.interrapidisimo.android.core.di

import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceRemoteAuthenticate
import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceRemoteAuthenticateImpl
import com.interrapidisimo.android.auth.data.repository.remote.AuthenticateRemoteRepository
import com.interrapidisimo.android.auth.data.repository.remote.AuthenticateRemoteRepositoryImpl
import com.interrapidisimo.android.auth.domain.remote.AuthenticateRemoteUseCase
import com.interrapidisimo.android.auth.domain.remote.AuthenticateRemoteUseCaseImpl
import com.interrapidisimo.android.localities.data.provider.remote.server.DataSourceRemoteLocalities
import com.interrapidisimo.android.localities.data.provider.remote.server.DataSourceRemoteLocalitiesImpl
import com.interrapidisimo.android.localities.data.repository.remote.LocalitiesRemoteRepository
import com.interrapidisimo.android.localities.data.repository.remote.LocalitiesRemoteRepositoryImpl
import com.interrapidisimo.android.localities.domain.remote.LocalitiesRemoteUseCase
import com.interrapidisimo.android.localities.domain.remote.LocalitiesRemoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {


    // AUTHENTICATE
    @Binds
    abstract fun authenticateRemoteUseCase(authenticateUsecase: AuthenticateRemoteUseCaseImpl): AuthenticateRemoteUseCase

    @Binds
    abstract fun authenticateRemoteRepository(authenticateRepo: AuthenticateRemoteRepositoryImpl): AuthenticateRemoteRepository

    @Binds
    abstract fun authenticateDatasourceRemote(authenticateDatasource: DataSourceRemoteAuthenticateImpl): DataSourceRemoteAuthenticate



    // LOCALITIES

    @Binds
    abstract fun localitiesRemoteUseCase(usecaseLocalities: LocalitiesRemoteUseCaseImpl): LocalitiesRemoteUseCase

    @Binds
    abstract fun localitiesRemoteRepository(localitiesRepository: LocalitiesRemoteRepositoryImpl): LocalitiesRemoteRepository

    @Binds
    abstract fun localitiesDatasourceRemote(localitiesDatasource: DataSourceRemoteLocalitiesImpl): DataSourceRemoteLocalities


}