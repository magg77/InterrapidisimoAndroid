package com.interrapidisimo.android.auth.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.domain.VersionAppUseCaseContract
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
    private val usecase: VersionAppUseCaseContract,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState: MutableStateFlow<ResourceState<StoreAppControl>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiState: StateFlow<ResourceState<StoreAppControl>> = _uiState

    private val _uiStateAuthenticate: MutableStateFlow<ResourceState<AuthenticateCustom>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiStateAuthenticate: StateFlow<ResourceState<AuthenticateCustom>> = _uiStateAuthenticate

    private val _uiStateValidate = MutableLiveData<String>() // Puedes usarlo para mostrar mensajes de error
    val uiStateValidate: LiveData<String> get() = _uiStateValidate



    fun getVersionApp() = viewModelScope.launch {
        usecase.getVpStoreAppControlUseCase(context)
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    /*nomAplicacion = "Controller APP",
    usuario = "cGFtLm1lcmVkeTIx\n",
    password = "SW50ZXIyMDIx\n"*/

    fun authenticate(nameApp: String, user: String, password: String) = viewModelScope.launch {



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

        usecase.authenticateDataSourceRepo(
            context,
            nomAplicacion = nameApp,
            user = user,
            password = password
        )
            .onEach { _uiStateAuthenticate.value = it }
            .launchIn(viewModelScope)
    }


}