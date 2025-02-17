package com.interrapidisimo.android.localities.data.provider.remote.server

import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.StreamResetException
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DataSourceRemoteLocalitiesImpl @Inject constructor(private val serviceLocalitiesRemote: WebServiceLocalities) :
    DataSourceRemoteLocalities {


    override suspend fun getLocalitiesRemote(): ResourceState<LocalitiesResponse> {

        return withContext(Dispatchers.IO){

            try {

                val responseLocalities = serviceLocalitiesRemote.getLocalities()

                if(responseLocalities.isSuccessful){

                    val body = responseLocalities.body()

                    if(body != null){

                        ResourceState.SuccessState(body)    // ✅ Éxito con datos

                    }else{

                        ResourceState.FailureState("Estamos trabajando para solucionarlo") // ⚠️ Cuerpo nulo

                    }

                }else{

                    val errorBody = responseLocalities.errorBody()?.string()

                    // ⚠️ Manejar códigos de error HTTP específicos
                    val errorMessage = when (responseLocalities.code()) {
                        400 -> errorBody ?: "Error en la solicitud"
                        401 -> errorBody ?: "No autorizado (Unauthorized)"
                        403 -> errorBody ?: "Prohibido (Forbidden)"
                        404 -> errorBody ?: "Recurso no encontrado (Not Found)"
                        500 -> errorBody ?: "Error interno del servidor"
                        else -> "Error desconocido: ${responseLocalities.code()}"
                    }

                    ResourceState.FailureState(errorMessage)

                }

            }catch (e: IOException) {
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