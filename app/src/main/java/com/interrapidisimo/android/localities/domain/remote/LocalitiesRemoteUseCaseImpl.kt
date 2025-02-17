package com.interrapidisimo.android.localities.domain.remote

import android.content.Context
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponse
import com.interrapidisimo.android.localities.data.repository.remote.LocalitiesRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalitiesRemoteUseCaseImpl @Inject constructor(private val repoLocalitiesRemote: LocalitiesRemoteRepository): LocalitiesRemoteUseCase {

    override suspend fun getLocalitiesRemoteUseCase(context: Context): Flow<ResourceState<LocalitiesResponse>> =
        repoLocalitiesRemote.getLocalitiesRemoteRepository(context)

}