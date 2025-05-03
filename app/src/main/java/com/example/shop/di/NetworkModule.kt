package com.example.shop.di

import com.example.shop.utils.Constants.TIMEOUT_IN_SECOND
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    fun provideOkHttp():OkHttpClient=OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_IN_SECOND,TimeUnit.SECONDS)
        .build()
}