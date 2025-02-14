package com.interrapidisimo.android.auth.domain

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.data.repository.RepositoryContract
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VersionAppUseCase @Inject constructor(private val repo: RepositoryContract) :
    VersionAppUseCaseContract {

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