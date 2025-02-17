package com.interrapidisimo.android.auth.domain.local

import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateLocalRepository
import javax.inject.Inject

class AuthenticateLocalUseCaseImpl @Inject constructor(private val authenticateRepo: AuthenticateLocalRepository) :
    AuthenticateLocalUseCase {

    override fun insertAuthenticateUseCase(auth: AuthenticateCustom) =
        authenticateRepo.insertAuthenticate(auth)

}