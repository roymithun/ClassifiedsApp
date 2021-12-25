package com.inhouse.classifiedsapp.core.data.repository

import androidx.lifecycle.LiveData
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ClassifiedsAdRepository {
    suspend fun fetchClassifiedsAdList(): Resource<ClassifiedAdList>
    fun flowClassifiedAds(): Flow<List<ClassifiedAd>>
    fun classifiedAdsForUid(uid: String): LiveData<ClassifiedAd>
}