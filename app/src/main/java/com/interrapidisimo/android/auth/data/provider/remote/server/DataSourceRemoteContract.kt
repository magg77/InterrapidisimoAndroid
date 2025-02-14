package com.interrapidisimo.android.auth.data.provider.remote.server

import com.interrapidisimo.android.auth.data.provider.remote.model.Authenticate
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.core.valueObjet.ResourceState
import okhttp3.ResponseBody

interface DataSourceRemoteContract {

    suspend fun getVpStoreAppControlDataSource(): ResourceState<ResponseBody>

    suspend fun authenticateDataSourceRemote(request: AuthenticateRequest): ResourceState<Authenticate>

}