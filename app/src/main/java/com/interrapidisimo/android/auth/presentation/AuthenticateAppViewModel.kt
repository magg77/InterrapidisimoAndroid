package com.interrapidisimo.android.auth.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.domain.local.AuthenticateUseCase
import com.interrapidisimo.android.auth.domain.remote.VersionAppUseCaseContract
import com.interrapidisimo.android.core.valueObjet.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateAppViewModel @Inject constructor(
    private val usecaseAuthenticateRemote: VersionAppUseCaseContract,
    private val usecaseAuthenticateLocal: AuthenticateUseCase,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiStateVersionApp: MutableStateFlow<ResourceState<StoreAppControl>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiStateVersionApp: StateFlow<ResourceState<StoreAppControl>> = _uiStateVersionApp

    private val _uiStateAuthenticate: MutableStateFlow<ResourceState<AuthenticateCustom>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiStateAuthenticate: StateFlow<ResourceState<AuthenticateCustom>> = _uiStateAuthenticate



    fun getVersionApp() = viewModelScope.launch {
        usecaseAuthenticateRemote.getVpStoreAppControlUseCase(context)
            .onEach { _uiStateVersionApp.value = it }
            .launchIn(viewModelScope)
    }

    fun authenticate(nameApp: String, user: String, password: String) = viewModelScope.launch {

        Log.i("authenticateAppViewModel", "desde viewmodel")

        // Validación de campos
        if (nameApp.isBlank()) {
            _uiStateAuthenticate.value = ResourceState.FailureState("El nombre de la aplicación no puede estar vacío")
            return@launch
        }
        if (user.isBlank()) {
            _uiStateAuthenticate.value = ResourceState.FailureState("El usuario no puede estar vacío")
            return@launch
        }
        if (password.isBlank()) {
            _uiStateAuthenticate.value = ResourceState.FailureState("La contraseña no puede estar vacía")
            return@launch
        }
        if (password.length < 6) {
            _uiStateAuthenticate.value = ResourceState.FailureState("La contraseña debe tener al menos 6 caracteres")
            return@launch
        }

        usecaseAuthenticateRemote.authenticateDataSourceRepo(
            context,
            nomAplicacion = nameApp,
            user = user,
            password = password
        )
            .onEach { _uiStateAuthenticate.value = it }
            .launchIn(viewModelScope)
    }

    fun saveAuthenticateBdSQLite(auth: AuthenticateCustom) {
        viewModelScope.launch {
            usecaseAuthenticateLocal.insertAuthenticateUseCase(auth)
        }
    }


}