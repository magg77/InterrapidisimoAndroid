package com.interrapidisimo.android.auth.domain.remote

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow

interface AuthenticateRemoteUseCase {

    suspend fun getVpStoreAppControlUseCase(context: Context): Flow<ResourceState<StoreAppControl>>

    suspend fun authenticateDataSourceRepo(
        context: Context,
        nomAplicacion: String,
        user: String,
        password: String
    ): Flow<ResourceState<AuthenticateCustom>>


}