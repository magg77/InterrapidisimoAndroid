package com.interrapidisimo.android.auth.domain.remote

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.data.repository.remote.AuthenticateRemoteRepository
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticateRemoteUseCaseImpl @Inject constructor(private val repo: AuthenticateRemoteRepository) :
    AuthenticateRemoteUseCase {

    override suspend fun getVpStoreAppControlUseCase(context: Context): Flow<ResourceState<StoreAppControl>> =
        repo.getVpStoreAppControlRepo(context)

    override suspend fun authenticateDataSourceRepo(
        context: Context,
        nomAplicacion: String,
        user: String,
        password: String
    ): Flow<ResourceState<AuthenticateCustom>> =
        repo.authenticateDataSourceRepo(context, nomAplicacion, user, password)


}