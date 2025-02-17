package com.interrapidisimo.android.auth.data.provider.remote.server

import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateResponse
import com.interrapidisimo.android.core.valueObjet.ResourceState
import okhttp3.ResponseBody

interface DataSourceRemoteAuthenticate {

    suspend fun getVpStoreAppControlDataSource(): ResourceState<ResponseBody>

    suspend fun authenticateDataSourceRemote(request: AuthenticateRequest): ResourceState<AuthenticateResponse>

}