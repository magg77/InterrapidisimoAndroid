package com.interrapidisimo.android.auth.data.provider.remote.server


import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateResponse
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.core.valueObjet.NoAuth
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebServiceAuthenticate {

    @GET("api/version")
    @NoAuth
    suspend fun getVpStoreAppControl(): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("auth")
    suspend fun authenticate(@Body request: AuthenticateRequest): Response<AuthenticateResponse>


}