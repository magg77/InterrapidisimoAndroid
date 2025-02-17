package com.interrapidisimo.android.localities.data.provider.remote.server

import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse

interface DataSourceRemoteLocalities {

    suspend fun getLocalitiesRemote(): ResourceState<LocalitiesResponse>

}