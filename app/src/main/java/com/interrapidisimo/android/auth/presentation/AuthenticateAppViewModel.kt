package com.interrapidisimo.android.auth.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getVersionApp(context: Context) = viewModelScope.launch {
        usecase.getVpStoreAppControlUseCase(context)
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }


}