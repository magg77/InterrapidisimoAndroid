package com.interrapidisimo.android.localities.data.provider.remote.server

import com.interrapidisimo.android.core.valueObjet.NoAuth
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServiceLocalities {

    @GET("api/localities")
    @NoAuth
    suspend fun getLocalities(): Response<LocalitiesResponse>


}