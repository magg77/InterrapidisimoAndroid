package com.interrapidisimo.android.auth.data.repository

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow

interface RepositoryContract {

    suspend fun getVpStoreAppControlRepo(context: Context): Flow<ResourceState<StoreAppControl>>

}