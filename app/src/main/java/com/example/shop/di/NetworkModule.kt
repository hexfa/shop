package com.example.shop.di

import com.example.shop.data.remote.ApiInterface
import com.example.shop.utils.Constants.BASE_URL
import com.example.shop.utils.Constants.TIMEOUT_IN_SECOND
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient=OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .build()
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiInterface=
        retrofit.create(ApiInterface::class.java)
}