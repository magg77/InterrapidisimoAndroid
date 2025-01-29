package com.interrapidisimo.android.auth.data.provider.remote.server

import retrofit2.http.GET

interface WebServiceContract {

    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun GetVpStoreAppControl()

}