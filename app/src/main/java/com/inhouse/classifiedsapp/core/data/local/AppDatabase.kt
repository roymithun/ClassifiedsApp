package com.inhouse.classifiedsapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inhouse.classifiedsapp.core.data.local.converter.RoomTypeConverter
import com.inhouse.classifiedsapp.core.model.ClassifiedAd

@Database(
    version = 1,
    entities = [ClassifiedAd::class],
    exportSchema = false
)
@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun classifiedAdDao(): ClassifiedAdDao
}