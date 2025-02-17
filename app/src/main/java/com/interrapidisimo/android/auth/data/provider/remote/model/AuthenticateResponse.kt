package com.interrapidisimo.android.auth.data.provider.remote.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AuthenticateResponse(
    @SerializedName("Apellido2")
    val apellido2: String,
    @SerializedName("Apellido8")
    val apellido8: String,
    @SerializedName("Aplicaciones")
    val aplicaciones: Aplicaciones,
    @SerializedName("Cargo")
    val cargo: @RawValue Any? = null,
    @SerializedName("IdLocalidad")
    val idLocalidad: String,
    @SerializedName("IdRol")
    val idRol: @RawValue Any? = null,
    @SerializedName("Identificacion")
    val identificacion: String,
    @SerializedName("MensajeResultado")
    val mensajeResultado: Int,
    @SerializedName("ModulosApp")
    val modulosApp: List<ModulosApp>,
    @SerializedName("NomRol")
    val nomRol: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("NombreLocalidad")
    val nombreLocalidad: String,
    @SerializedName("TokenJWT")
    val tokenJWT: @RawValue Any? = null,
    @SerializedName("Ubicaciones")
    val ubicaciones: List<Ubicacione>,
    @SerializedName("Usuario")
    val usuario: String
): Parcelable

@Parcelize
data class Aplicaciones(
    @SerializedName("APLEstado")
    val aPLEstado: Boolean,
    @SerializedName("APLIdAplicacion")
    val aPLIdAplicacion: Int,
    @SerializedName("APLNombreAplicacion")
    val aPLNombreAplicacion: String,
    @SerializedName("APLNombreCorto")
    val aPLNombreCorto: @RawValue Any? = null,
    @SerializedName("APLRutaAplicacion")
    val aPLRutaAplicacion:  @RawValue Any? = null,
    @SerializedName("APLVersion")
    val aPLVersion:  @RawValue Any? = null,
    @SerializedName("Modulos")
    val modulos: List<Modulo>,
    @SerializedName("Paginas")
    val paginas: Int,
    @SerializedName("TotalPaginas")
    val totalPaginas: Int
):Parcelable

@Parcelize
data class ModulosApp(
    @SerializedName("MODEstado")
    val mODEstado: Boolean,
    @SerializedName("MODIdAplicacion")
    val mODIdAplicacion: Int,
    @SerializedName("MODIdModulo")
    val mODIdModulo: Int,
    @SerializedName("MODNombre")
    val mODNombre: String,
    @SerializedName("MODOrden")
    val mODOrden: Int,
    @SerializedName("MODVisible")
    val mODVisible: Boolean
):Parcelable

@Parcelize
data class Ubicacione(
    @SerializedName("IdCaja")
    val idCaja: Int,
    @SerializedName("IdLocalidad")
    val idLocalidad: String,
    @SerializedName("NombreCentroServicios")
    val nombreCentroServicios: String,
    @SerializedName("NombreCompletoLocalidad")
    val nombreCompletoLocalidad: String,
    @SerializedName("UBUIdCentroServicios")
    val uBUIdCentroServicios: Int,
    @SerializedName("UBUIdUsuario")
    val uBUIdUsuario: String
):Parcelable

@Parcelize
data class Modulo(
    @SerializedName("APLNombreAplicacion")
    val aPLNombreAplicacion: @RawValue Any? = null,
    @SerializedName("MODEstado")
    val mODEstado: Boolean,
    @SerializedName("MODIdAplicacion")
    val mODIdAplicacion: Int,
    @SerializedName("MODIdModulo")
    val mODIdModulo: Int,
    @SerializedName("MODNombre")
    val mODNombre: String,
    @SerializedName("MODNombreCorto")
    val mODNombreCorto:  @RawValue Any? = null,
    @SerializedName("Menus")
    val menus: List<Menu>,
    @SerializedName("TotalPaginas")
    val totalPaginas: Int
):Parcelable

@Parcelize
data class Menu(
    @SerializedName("APLNombreAplicacion")
    val aPLNombreAplicacion:  @RawValue Any? = null,
    @SerializedName("AccionesMenu")
    val accionesMenu: List<AccionesMenu>,
    @SerializedName("MENEstado")
    val mENEstado: Boolean,
    @SerializedName("MENIcono")
    val mENIcono: String,
    @SerializedName("MENIdMenu")
    val mENIdMenu: Int,
    @SerializedName("MENIdMenuPadre")
    val mENIdMenuPadre: Int,
    @SerializedName("MENIdModulo")
    val mENIdModulo: Int,
    @SerializedName("MENNombre")
    val mENNombre: String,
    @SerializedName("MENNombreCorto")
    val mENNombreCorto: String,
    @SerializedName("MENObservacion")
    val mENObservacion:  @RawValue Any? = null,
    @SerializedName("MENUrl")
    val mENUrl: String,
    @SerializedName("MODNombre")
    val mODNombre:  @RawValue Any? = null,
    @SerializedName("SubMenu")
    val subMenu: List<String>? = emptyList(),
    @SerializedName("TotalPaginas")
    val totalPaginas: Int
):Parcelable

@Parcelize
data class AccionesMenu(
    @SerializedName("ACCNombre")
    val aCCNombre:  @RawValue Any? = null,
    @SerializedName("MEAEstado")
    val mEAEstado: Boolean,
    @SerializedName("MEAIdAccion")
    val mEAIdAccion: Int,
    @SerializedName("MEAIdMenu")
    val mEAIdMenu: Int,
    @SerializedName("MEAIdMenuAccion")
    val mEAIdMenuAccion: Int
):Parcelable


//custom object Authenticate
@Parcelize
data class AuthenticateCustom(
    @SerializedName("identificacion") val identificacion: String = "",
    @SerializedName("usuario") val usuario: String = "",
    @SerializedName("nombre") val nombre: String = ""
): Parcelable

fun AuthenticateResponse.toAuthenticateCustom(): AuthenticateCustom = AuthenticateCustom(
    identificacion = this.identificacion,
    usuario = this.usuario,
    nombre = this.nombre
)