package com.inhouse.classifiedsapp.core.data.repository

import com.inhouse.classifiedsapp.core.data.remote.ApiClient
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object DefaultClassifiedsAdRepository : ClassifiedsAdRepository {
    override suspend fun getClassifiedsAdList(): Resource<ClassifiedAdList> {
        return try {
            withContext(Dispatchers.IO) {
                Resource.Success(null)
                val articleListResponse: Response<ClassifiedAdList> =
                    ApiClient.apiService.getClassifiedsList()
                if (articleListResponse.isSuccessful) {
                    articleListResponse.body()?.let {
                        // if successful response insert into db
//                        it.let {
//                            articleDao.insertArticleList(it.results)
//                        }
                        return@let Resource.Success(it)
                    } ?: Resource.Failed("Some unknown error occurred")
                } else {
                    Resource.Failed("Some unknown error occurred")
                }
            }
        } catch (e: Exception) {
            Resource.Failed("Couldn't reach the server. Check your internet connection")
        }
    }
}