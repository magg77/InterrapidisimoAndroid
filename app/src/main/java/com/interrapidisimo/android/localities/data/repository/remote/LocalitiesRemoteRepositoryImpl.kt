package com.interrapidisimo.android.localities.data.repository.remote

import android.content.Context
import com.interrapidisimo.android.core.utils.ConnectionManager
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponseItem
import com.interrapidisimo.android.localities.data.provider.remote.server.DataSourceRemoteLocalities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class LocalitiesRemoteRepositoryImpl @Inject constructor(private val dataSourceRemoteLocalities: DataSourceRemoteLocalities) :
    LocalitiesRemoteRepository {

    override suspend fun getLocalitiesRemoteRepository(context: Context): Flow<ResourceState<LocalitiesResponse>> =
        channelFlow {

            // Verificar la conexión de red
            if (!ConnectionManager.isNetworkAvailable(context)) {
                send(ResourceState.FailureState("No hay conexión de red"))
            }


            //emitir estado de carga
            send(ResourceState.LoadingState())

            val responseLocalities = dataSourceRemoteLocalities.getLocalitiesRemote()

            // Obtener la respuesta de la fuente remota
            when(val responseLocalities = dataSourceRemoteLocalities.getLocalitiesRemote()){

                is ResourceState.SuccessState -> {
                    var datadd = responseLocalities.data
                    send(ResourceState.SuccessState(responseLocalities.data)) // Emitir éxito
                }

                is ResourceState.FailureState -> {
                    send(ResourceState.FailureState(responseLocalities.message))
                }

                else -> {
                    send(ResourceState.FailureState("Error desconocido"))
                }

            }


        }


}

