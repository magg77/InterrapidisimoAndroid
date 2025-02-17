package com.interrapidisimo.android.auth.data.provider.remote.server

import android.util.Log
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateResponse
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.internal.http2.StreamResetException
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class DataSourceRemoteAuthenticateImpl @Inject constructor(private val serviceAuthenticateRemote: WebServiceAuthenticate) :
    DataSourceRemoteAuthenticate {

    override suspend fun getVpStoreAppControlDataSource(): ResourceState<ResponseBody> {

        return withContext(Dispatchers.IO) {

            try {

                val response = serviceAuthenticateRemote.getVpStoreAppControl()

                if (response.isSuccessful) {

                    val body: ResponseBody? = response.body()
                    if (body != null) {
                        ResourceState.SuccessState(body) // ✅ Éxito con datos
                    } else {
                        ResourceState.FailureState("Estamos trabajando para solucionarlo") // ⚠️ Cuerpo nulo
                    }

                } else {

                    val errorBody = response.errorBody()?.string()

                    // ⚠️ Manejar códigos de error HTTP específicos
                    val errorMessage = when (response.code()) {
                        400 -> errorBody ?: "Error en la solicitud"
                        401 -> errorBody ?: "No autorizado (Unauthorized)"
                        403 -> errorBody ?: "Prohibido (Forbidden)"
                        404 -> errorBody ?: "Recurso no encontrado (Not Found)"
                        500 -> errorBody ?: "Error interno del servidor"
                        else -> "Error desconocido: ${response.code()}"
                    }

                    ResourceState.FailureState(errorMessage)

                }

            } catch (e: IOException) {
                ResourceState.FailureState("Error de red: ${e.message}") // Sin conexión o timeout
            } catch (e: HttpException) {
                ResourceState.FailureState("Error HTTP ${e.code()}: ${e.message()}") // Errores HTTP (400, 500, etc.)
            } catch (e: StreamResetException) {
                ResourceState.FailureState("Conexión interrumpida: ${e.message}") // Errores de streaming en HTTP2
            } catch (e: Exception) {
                ResourceState.FailureState("Error desconocido: ${e.localizedMessage}")
            }

        }

    }

    override suspend fun authenticateDataSourceRemote(request: AuthenticateRequest): ResourceState<AuthenticateResponse> {

        return withContext(Dispatchers.IO) {

            try {

                val response: Response<AuthenticateResponse> = serviceAuthenticateRemote.authenticate(request)

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null) {
                        ResourceState.SuccessState(body) // ✅ Éxito con datos
                    } else {
                        ResourceState.FailureState("Estamos trabajando para solucionarlo") // ⚠️ Cuerpo nulo
                    }

                } else {

                    val errorBody = response.errorBody()?.string()

                    // ⚠️ Manejar códigos de error HTTP específicos
                    val errorMessage = when (response.code()) {
                        400 -> errorBody ?: "Error en la solicitud"
                        401 -> errorBody ?: "No autorizado (Unauthorized)"
                        403 -> errorBody ?: "Prohibido (Forbidden)"
                        404 -> errorBody ?: "Recurso no encontrado (Not Found)"
                        500 -> errorBody ?: "Error interno del servidor"
                        else -> "Error desconocido: ${response.code()}"
                    }

                    ResourceState.FailureState(errorMessage)

                }

            } catch (e: IOException) {
                ResourceState.FailureState("Error de red: ${e.message}") // Sin conexión o timeout
            } catch (e: HttpException) {
                ResourceState.FailureState("Error HTTP ${e.code()}: ${e.message()}") // Errores HTTP (400, 500, etc.)
            } catch (e: StreamResetException) {
                ResourceState.FailureState("Conexión interrumpida: ${e.message}") // Errores de streaming en HTTP2
            } catch (e: Exception) {
                ResourceState.FailureState("Error desconocido: ${e.localizedMessage}")
            }

        }

    }

}