package com.inhouse.classifiedsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inhouse.classifiedsapp.core.data.repository.ClassifiedsAdRepository
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ClassifiedsAdRepository,
) :
    ViewModel() {
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    val classifiedsAdsListFlow = repository.flowClassifiedAds()
        .stateIn(viewModelScope, WhileSubscribed(), emptyList())

    init {
        fetchClassifiedAds()
    }

    fun fetchClassifiedAds() {
        _showProgress.postValue(true)
        viewModelScope.launch {
            val classifiedsAdListResource: Resource<ClassifiedAdList> =
                repository.fetchClassifiedsAdList()
            _showProgress.postValue(false)
            when (classifiedsAdListResource) {
                is Resource.Success ->
                    Log.d(
                        "HomeViewModel",
                        "ClassifiedAd date=${classifiedsAdListResource.data}}"
                    )

                is Resource.Failed -> Log.d(
                    "HomeViewModel",
                    "classifiedsAdListResource failure = ${classifiedsAdListResource.message}"
                )
            }
        }
    }
}