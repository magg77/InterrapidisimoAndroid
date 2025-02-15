package com.interrapidisimo.android.core.di

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.local.SQLiteHelper
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateRepository
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateRepositoryImpl
import com.interrapidisimo.android.auth.domain.local.AuthenticateUseCase
import com.interrapidisimo.android.auth.domain.local.AuthenticateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticateUseCase(authenticateUsecase: AuthenticateUseCaseImpl): AuthenticateUseCase

    @Binds
    @Singleton
    abstract fun bindAuthenticateRepository(
        repositoryImpl: AuthenticateRepositoryImpl
    ): AuthenticateRepository

    companion object {
        @Provides
        @Singleton
        fun provideSQLiteHelper(@ApplicationContext context: Context): SQLiteHelper {
            return SQLiteHelper(context)
        }
    }
}
