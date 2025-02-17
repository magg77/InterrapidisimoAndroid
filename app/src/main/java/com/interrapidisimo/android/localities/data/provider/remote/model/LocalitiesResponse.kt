package com.interrapidisimo.android.localities.data.provider.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/*@Parcelize
data class LocalitiesResponse (
    val listLocalities:  List<LocalitiesResponseItem>
): Parcelable*/

typealias LocalitiesResponse = List<LocalitiesResponseItem>

@Parcelize
data class LocalitiesResponseItem(
    @SerializedName("AbreviacionCiudad")
    val abreviacionCiudad: String,
    @SerializedName("AsignadoEnZona")
    val asignadoEnZona: Boolean,
    @SerializedName("AsignadoEnZonaOrig")
    val asignadoEnZonaOrig: Boolean,
    @SerializedName("CodigoPostal")
    val codigoPostal: String,
    @SerializedName("DispoLocalidad")
    val dispoLocalidad: Boolean,
    @SerializedName("EstadoRegistro")
    val estadoRegistro: Int,
    @SerializedName("EtiquetaEntrega")
    val etiquetaEntrega: Boolean,
    @SerializedName("HoraMaxRecogida")
    val horaMaxRecogida: Int,
    @SerializedName("HoraMinRecogida")
    val horaMinRecogida: Int,
    @SerializedName("IdAncestroPGrado")
    val idAncestroPGrado: String,
    @SerializedName("IdAncestroSGrado")
    val idAncestroSGrado: String,
    @SerializedName("IdAncestroTGrado")
    val idAncestroTGrado: @RawValue Any? = null,
    @SerializedName("IdCentroServicio")
    val idCentroServicio: Int,
    @SerializedName("IdLocalidad")
    val idLocalidad: String,
    @SerializedName("IdTipoLocalidad")
    val idTipoLocalidad: String,
    @SerializedName("Indicativo")
    val indicativo: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("NombreAncestroPGrado")
    val nombreAncestroPGrado: String,
    @SerializedName("NombreAncestroSGrado")
    val nombreAncestroSGrado: @RawValue Any? = null,
    @SerializedName("NombreAncestroTGrado")
    val nombreAncestroTGrado: @RawValue Any? = null,
    @SerializedName("NombreCompleto")
    val nombreCompleto: String,
    @SerializedName("NombreCorto")
    val nombreCorto: String,
    @SerializedName("NombreTipoLocalidad")
    val nombreTipoLocalidad: @RawValue Any? = null,
    @SerializedName("NombreZona")
    val nombreZona: String?,
    @SerializedName("PermitePreEnviosPunto")
    val permitePreEnviosPunto: Boolean,
    @SerializedName("PermiteRecogida")
    val permiteRecogida: Boolean,
    @SerializedName("SeGeorreferencia")
    val seGeorreferencia: Boolean,
    @SerializedName("TiposLocalidades")
    val tiposLocalidades: @RawValue Any? = null,
    @SerializedName("ValorRecogida")
    val valorRecogida: Double
): Parcelable