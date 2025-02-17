package com.interrapidisimo.android.localities.domain.remote

import android.content.Context
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import kotlinx.coroutines.flow.Flow

interface LocalitiesRemoteUseCase {

    suspend fun getLocalitiesRemoteUseCase(context: Context): Flow<ResourceState<LocalitiesResponse>>

}