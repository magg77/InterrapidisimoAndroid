package com.interrapidisimo.android.core.di

import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceRemoteImpl
import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceRemoteContract
import com.interrapidisimo.android.auth.data.repository.remote.RepositoryContract
import com.interrapidisimo.android.auth.data.repository.remote.RepositoryImpl
import com.interrapidisimo.android.auth.domain.remote.VersionAppUseCase
import com.interrapidisimo.android.auth.domain.remote.VersionAppUseCaseContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindConstractsModule {

    @Binds
    abstract fun getVpStoreAppControlUseCase(userCase: VersionAppUseCase): VersionAppUseCaseContract

    @Binds
    abstract fun getVpStoreAppControlRepo(repo: RepositoryImpl): RepositoryContract

    @Binds
    abstract fun getVpStoreAppControlDataSource(dataRemote: DataSourceRemoteImpl): DataSourceRemoteContract

}