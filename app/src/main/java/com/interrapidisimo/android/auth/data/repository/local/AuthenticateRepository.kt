package com.interrapidisimo.android.auth.data.repository.local

import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom

interface AuthenticateRepository {

    fun insertAuthenticate(auth: AuthenticateCustom)
    fun getAuthenticateById(identificacion: String): AuthenticateCustom?
    fun deleteAuthenticate(identificacion: String)

}