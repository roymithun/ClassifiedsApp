package com.inhouse.classifiedsapp.core.data.remote

import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("dynamodb-writer")
    suspend fun getClassifiedsList(): Response<ClassifiedAdList>
}