package com.interrapidisimo.android.core.valueObjet

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class CustomHeadersInterceptor(private val headersProvider: () -> Map<String, String>) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        // Obtener las anotaciones del método
        val annotations = originalRequest.tag(Invocation::class.java)?.method()?.annotations


        // Si el endpoint tiene @NoAuth, no agregamos headers adicionales
        if (annotations?.any { it is NoAuth } == true) {
            return chain.proceed(originalRequest)
        }

        // Construimos la nueva request eliminando Content-Type si ya existe
        val requestBuilder = originalRequest.newBuilder()
            .removeHeader("Content-Type") // Asegurar que no haya duplicados
            .addHeader("Content-Type", "application/json") // Agregar el correcto

        // Agregar los demás headers personalizados
        headersProvider().forEach { (key, value) ->
            if (key != "Content-Type") {
                requestBuilder.addHeader(key, value)
            }
        }

        return chain.proceed(requestBuilder.build())

    }

}