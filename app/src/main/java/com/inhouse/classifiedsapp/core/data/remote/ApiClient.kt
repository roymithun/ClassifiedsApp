package com.inhouse.classifiedsapp.core.data.remote

import com.inhouse.classifiedsapp.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val apiService: ApiService
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(40, TimeUnit.SECONDS)
            httpClient.readTimeout(40, TimeUnit.SECONDS)
            httpClient.writeTimeout(40, TimeUnit.SECONDS)

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(httpClient.build())
                .baseUrl(BASE_URL).build()

            return retrofit.create(ApiService::class.java)
        }
}