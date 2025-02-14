package com.interrapidisimo.android.core.di

import com.google.gson.GsonBuilder
import com.interrapidisimo.android.BuildConfig
import com.interrapidisimo.android.core.utils.Constants
import com.interrapidisimo.android.auth.data.provider.remote.server.WebServiceContract
import com.interrapidisimo.android.core.valueObjet.CustomHeadersInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRetrofitClient {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val timeout = 30
        return OkHttpClient.Builder()
            // interceptor token header
            .addInterceptor(CustomHeadersInterceptor {
                mapOf(
                    "Usuario" to "pam.meredy21",
                    "pam.meredy21" to "987204545",
                    "IdUsuario" to "pam.meredy21",
                    "IdCentroServicio" to "1295",
                    "NombreCentroServicio" to "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA30#7-45",
                    "IdAplicativoOrigen" to "9",
                    "Accept" to "text/json",
                    "text/json" to "application/json"
                )
            })
            // HTTP log interceptor in debug mode only
            .also { okHttpClient: OkHttpClient.Builder ->
                if (BuildConfig.DEBUG) {
                    val httpLogginInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    okHttpClient.addInterceptor(httpLogginInterceptor)
                }
            }
            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkRetrofitModule(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideWebServiceInterface(retrofit: Retrofit): WebServiceContract {
        return retrofit.create(WebServiceContract::class.java)
    }


}