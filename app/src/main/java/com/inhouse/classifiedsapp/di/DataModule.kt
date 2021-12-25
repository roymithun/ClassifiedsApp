package com.inhouse.classifiedsapp.di

import android.content.Context
import androidx.room.Room
import com.inhouse.classifiedsapp.core.data.local.AppDatabase
import com.inhouse.classifiedsapp.core.data.local.ClassifiedAdDao
import com.inhouse.classifiedsapp.core.data.local.converter.RoomTypeConverter
import com.inhouse.classifiedsapp.utils.DATABASE_NAME
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun roomTypeConverter() = RoomTypeConverter(Moshi.Builder().build())

    @Singleton
    @Provides
    fun classifiedAdsDao(
        @ApplicationContext context: Context,
        roomTypeConverter: RoomTypeConverter
    ): ClassifiedAdDao =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(roomTypeConverter).build().classifiedAdDao()
}
