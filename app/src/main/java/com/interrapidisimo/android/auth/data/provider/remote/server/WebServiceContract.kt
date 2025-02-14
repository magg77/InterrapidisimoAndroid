package com.interrapidisimo.android.auth.data.provider.remote.server


import com.interrapidisimo.android.core.valueObjet.NoAuth
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebServiceContract {

    @GET("api/version")
    @NoAuth
    suspend fun getVpStoreAppControl(): Response<ResponseBody>

    /*@POST("auth")
    suspend fun authenticate(
        @HeaderMap headers: Map<String, String> // optional headers
    ): Response<>*/

    /*mapOf(
    "Usuario" to "pam.meredy21",
    "pam.meredy21" to "987204545",
    "IdUsuario" to "pam.meredy21",
    "IdCentroServicio" to "1295",
    "NombreCentroServicio" to "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA30#7-45",
    "IdAplicativoOrigen" to "9",
    "Accept" to "text/json",
    "text/json" to "application/json"
    )*/


}