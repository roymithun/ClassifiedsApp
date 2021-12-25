package com.inhouse.classifiedsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inhouse.classifiedsapp.core.data.repository.ClassifiedsAdRepository;
import com.inhouse.classifiedsapp.core.model.ClassifiedAd;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {
    private final ClassifiedsAdRepository repository;

    @Inject
    DetailViewModel(ClassifiedsAdRepository repository) {
        this.repository = repository;
    }

    public LiveData<ClassifiedAd> getClassifiedsAdForUid(String uid) {
        return repository.classifiedAdsForUid(uid);
    }
}
