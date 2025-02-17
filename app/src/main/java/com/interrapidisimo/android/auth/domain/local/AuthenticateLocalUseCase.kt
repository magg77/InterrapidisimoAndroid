package com.interrapidisimo.android.auth.domain.local

import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom

interface AuthenticateLocalUseCase{

    fun insertAuthenticateUseCase(auth: AuthenticateCustom)

}