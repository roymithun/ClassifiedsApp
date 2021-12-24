package com.inhouse.classifiedsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inhouse.classifiedsapp.core.data.repository.ClassifiedsAdRepository
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.core.model.createdAtDate
import com.inhouse.classifiedsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ClassifiedsAdRepository
) :
    ViewModel() {

    fun fetchClassifiedAds() {
        viewModelScope.launch {
            val classifiedsAdListResource: Resource<ClassifiedAdList> =
                repository.getClassifiedsAdList()
            when (classifiedsAdListResource) {
                is Resource.Success -> {
                    val list = classifiedsAdListResource.data
                    list.results.forEach {
                        Log.d(
                            "HomeViewModel",
                            "ClassifiedAd date=${it.createdAtDate()} createdAt=${it.createdAt}"
                        )
                    }
                }
                is Resource.Failed -> Log.d(
                    "HomeViewModel",
                    "classifiedsAdListResource failure = ${classifiedsAdListResource.message}"
                )
            }
        }
    }
}