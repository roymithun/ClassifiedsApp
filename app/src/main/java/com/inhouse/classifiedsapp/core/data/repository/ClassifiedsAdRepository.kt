package com.inhouse.classifiedsapp.core.data.repository

import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.Resource

interface ClassifiedsAdRepository {
    suspend fun getClassifiedsAdList(): Resource<ClassifiedAdList>
}