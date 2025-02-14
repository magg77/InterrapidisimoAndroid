package com.interrapidisimo.android.auth.presentation

import android.content.Context
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


    fun getVersionApp() = viewModelScope.launch {
        usecase.getVpStoreAppControlUseCase(context)
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    fun authenticate() = viewModelScope.launch {
        usecase.authenticateDataSourceRepo(
            context,
            nomAplicacion = "Controller APP",
            usuario = "cGFtLm1lcmVkeTIx\n",
            password = "SW50ZXIyMDIx\n"
        )
            .onEach { _uiStateAuthenticate.value = it }
            .launchIn(viewModelScope)
    }


}