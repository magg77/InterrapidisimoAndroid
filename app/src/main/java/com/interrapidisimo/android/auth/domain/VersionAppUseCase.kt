package com.interrapidisimo.android.auth.domain

import android.content.Context
import com.interrapidisimo.android.auth.data.provider.remote.model.StoreAppControl
import com.interrapidisimo.android.auth.data.repository.RepositoryContract
import com.interrapidisimo.android.core.valueObjet.ResourceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VersionAppUseCase @Inject constructor(private val repo: RepositoryContract): VersionAppUseCaseContract {

    override suspend fun getVpStoreAppControlUseCase(context: Context): Flow<ResourceState<StoreAppControl>> =
        repo.getVpStoreAppControlRepo(context)

}