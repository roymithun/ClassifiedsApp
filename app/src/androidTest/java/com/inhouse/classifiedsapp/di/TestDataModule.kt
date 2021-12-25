package com.inhouse.classifiedsapp.di

import android.content.Context
import androidx.room.Room
import com.inhouse.classifiedsapp.core.data.local.AppDatabase
import com.inhouse.classifiedsapp.core.data.local.ClassifiedAdDao
import com.inhouse.classifiedsapp.core.data.local.converter.RoomTypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object TestDataModule {
    @Singleton
    @Provides
    fun typeConverter() = RoomTypeConverter(Moshi.Builder().build())

    @Singleton
    @Provides
    fun provideInMemoryDb(
        @ApplicationContext context: Context,
        typeConverter: RoomTypeConverter
    ): ClassifiedAdDao =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(typeConverter)
            .build().classifiedAdDao()
}