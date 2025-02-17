package com.interrapidisimo.android.localities.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponseItem
import com.interrapidisimo.android.localities.domain.remote.LocalitiesRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalitiesViewModel @Inject constructor(
    private val useCaseRemoteLocalities: LocalitiesRemoteUseCase,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    private val _uiStateLocalities: MutableStateFlow<ResourceState<LocalitiesResponse>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiStateLocalities: StateFlow<ResourceState<LocalitiesResponse>> = _uiStateLocalities

    fun getLocalitiesViewModel() = viewModelScope.launch {
        useCaseRemoteLocalities.getLocalitiesRemoteUseCase(context)
            .onEach { _uiStateLocalities.value = it }
            .launchIn(viewModelScope)
    }


}