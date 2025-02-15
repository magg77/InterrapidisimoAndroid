package com.interrapidisimo.android.auth.domain.local

import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import com.interrapidisimo.android.auth.data.repository.local.AuthenticateRepository
import javax.inject.Inject

class AuthenticateUseCaseImpl @Inject constructor(private val authenticateRepo: AuthenticateRepository) :
    AuthenticateUseCase {

    override fun insertAuthenticateUseCase(auth: AuthenticateCustom) =
        authenticateRepo.insertAuthenticate(auth)

}