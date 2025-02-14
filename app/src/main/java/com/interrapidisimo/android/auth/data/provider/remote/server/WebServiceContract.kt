package com.interrapidisimo.android.auth.data.provider.remote.server


import com.interrapidisimo.android.auth.data.provider.remote.model.Authenticate
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.core.valueObjet.NoAuth
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebServiceContract {

    @GET("api/version")
    @NoAuth
    suspend fun getVpStoreAppControl(): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("auth")
    suspend fun authenticate(@Body request: AuthenticateRequest): Response<Authenticate>


}