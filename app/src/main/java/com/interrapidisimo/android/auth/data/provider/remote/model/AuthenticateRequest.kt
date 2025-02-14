package com.interrapidisimo.android.auth.data.provider.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AuthenticateRequest(
    @SerializedName("Mac") val mac: String = "",
    @SerializedName("NomAplicacion") val nomAplicacion: String = "",
    @SerializedName("Password") val password: String,
    @SerializedName("Path") val path: String = "",
    @SerializedName("Usuario") val usuario: String
): Parcelable
