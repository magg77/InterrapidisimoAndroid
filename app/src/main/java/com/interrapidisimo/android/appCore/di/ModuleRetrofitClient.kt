package com.interrapidisimo.android.appCore.di

import com.google.gson.GsonBuilder
import com.interrapidisimo.android.BuildConfig
import com.interrapidisimo.android.appCore.valueObjet.Constants
import com.interrapidisimo.android.auth.data.provider.remote.server.WebServiceContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRetrofitClient {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val timeout = 30
        return OkHttpClient.Builder()
            //interceptor token header
            .addInterceptor { chain: Interceptor.Chain ->
                chain.proceed(chain.request().newBuilder().also { }.build())
            }
            //interceptor token log http
            .also { okHttpClient: OkHttpClient.Builder ->
                if (BuildConfig.DEBUG) {
                    val httpLogginInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    okHttpClient.addInterceptor(httpLogginInterceptor)
                }
            }.build()
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