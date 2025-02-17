package com.interrapidisimo.android.core.di

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.local.SQLiteHelper
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateLocalRepository
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateLocalRepositoryImpl
import com.interrapidisimo.android.auth.domain.local.AuthenticateLocalUseCase
import com.interrapidisimo.android.auth.domain.local.AuthenticateLocalUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModuleSQLite {

    @Binds
    @Singleton
    abstract fun bindAuthenticateUseCase(authenticateUsecase: AuthenticateLocalUseCaseImpl): AuthenticateLocalUseCase

    @Binds
    @Singleton
    abstract fun bindAuthenticateRepository(
        repositoryImpl: AuthenticateLocalRepositoryImpl
    ): AuthenticateLocalRepository

    companion object {
        @Provides
        @Singleton
        fun provideSQLiteHelper(@ApplicationContext context: Context): SQLiteHelper {
            return SQLiteHelper(context)
        }
    }
}
