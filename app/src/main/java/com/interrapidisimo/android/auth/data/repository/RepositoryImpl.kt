package com.interrapidisimo.android.auth.data.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import com.interrapidisimo.android.auth.data.provider.remote.model.Authenticate
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateRequest
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.data.provider.remote.model.toAuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.server.DataSourceRemoteContract
import com.interrapidisimo.android.core.utils.ConnectionManager
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSourceRemote: DataSourceRemoteContract) :
    RepositoryContract {

    override suspend fun getVpStoreAppControlRepo(context: Context): Flow<ResourceState<StoreAppControl>> =
        channelFlow {

            //emitir estado de carga
            send(ResourceState.LoadingState())

            if (ConnectionManager.isNetworkAvailable(context)) {

                when (val response = dataSourceRemote.getVpStoreAppControlDataSource()) {

                    //emitir exito
                    is ResourceState.SuccessState -> {

                        val version = response.data.string()

                        send(ResourceState.SuccessState(StoreAppControl(versionApp = version)))
                    }

                    is ResourceState.FailureState -> {
                        send(ResourceState.FailureState(response.message))
                    }

                    else -> {}

                }

            } else {
                // Emitir error por red
                send(ResourceState.FailureState("No hay conexión de red"))
            }

        }

    override suspend fun authenticateDataSourceRepo(
        context: Context,
        nomAplicacion: String,
        user: String,
        password: String
    ): Flow<ResourceState<AuthenticateCustom>> =
        channelFlow {

            // Verificar la conexión de red
            if (!ConnectionManager.isNetworkAvailable(context)) {
                send(ResourceState.FailureState("No hay conexión de red"))
            }

            val encodedUser = Base64.encodeToString(user.toByteArray(), Base64.DEFAULT)
            val encodedPassword = Base64.encodeToString(password.toByteArray(), Base64.DEFAULT)

            val request = AuthenticateRequest(
                nomAplicacion = nomAplicacion,
                user = encodedUser,
                password = encodedPassword,
                mac = "",
                path = ""
            )

            when (val response = dataSourceRemote.authenticateDataSourceRemote(request)) {

                is ResourceState.SuccessState -> {
                    val auth: AuthenticateCustom = response.data.toAuthenticateCustom()

                    send(ResourceState.SuccessState(auth))
                }

                is ResourceState.FailureState -> {
                    send(ResourceState.FailureState(response.message))
                }

                else -> {
                    send(ResourceState.FailureState("Error desconocido"))
                }

            }

        }


}