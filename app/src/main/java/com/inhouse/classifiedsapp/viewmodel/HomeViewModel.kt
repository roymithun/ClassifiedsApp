package com.inhouse.classifiedsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inhouse.classifiedsapp.core.data.repository.ClassifiedsAdRepository
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ClassifiedsAdRepository
) :
    ViewModel() {

    private val _classifiedAdList = MutableLiveData<List<ClassifiedAd>>()
    val classifiedAdList: LiveData<List<ClassifiedAd>>
        get() = _classifiedAdList

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    fun fetchClassifiedAds() {
        _showProgress.postValue(true)
        viewModelScope.launch {
            val classifiedsAdListResource: Resource<ClassifiedAdList> =
                repository.getClassifiedsAdList()
            _showProgress.postValue(false)
            when (classifiedsAdListResource) {
                is Resource.Success -> {
                    val list = classifiedsAdListResource.data
                    _classifiedAdList.postValue(list.results)
                    /*list.results.forEach {
                        Log.d(
                            "HomeViewModel",
                            "ClassifiedAd date=${it.createdAtDate()} createdAt=${it.createdAt}"
                        )
                    }*/
                }
                is Resource.Failed -> Log.d(
                    "HomeViewModel",
                    "classifiedsAdListResource failure = ${classifiedsAdListResource.message}"
                )
            }
        }
    }
}