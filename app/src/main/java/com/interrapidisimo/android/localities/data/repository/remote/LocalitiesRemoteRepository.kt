package com.interrapidisimo.android.localities.data.repository.remote

import android.content.Context
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import kotlinx.coroutines.flow.Flow

interface LocalitiesRemoteRepository {

    suspend fun getLocalitiesRemoteRepository(context: Context): Flow<ResourceState<LocalitiesResponse>>

}