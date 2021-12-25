package com.inhouse.classifiedsapp.di

import com.inhouse.classifiedsapp.core.data.repository.ClassifiedsAdRepository
import com.inhouse.classifiedsapp.core.data.repository.DefaultClassifiedsAdRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @ViewModelScoped
    @Binds
    abstract fun bindClassifiedsAdRepository(repository: DefaultClassifiedsAdRepository): ClassifiedsAdRepository
}