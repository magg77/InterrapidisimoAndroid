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

        // Si tiene @NoAuth, no agregar headers
        val requestBuilder = if (annotations?.any { it is NoAuth } == true) {
            originalRequest.newBuilder()
        } else {
            originalRequest.newBuilder().apply {
                headersProvider().forEach({ (key, value) ->
                    addHeader(key, value)
                })
            }
        }

        //add dynamic headers
        /*headersProvider().forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }*/

        return chain.proceed(requestBuilder.build())

    }

}