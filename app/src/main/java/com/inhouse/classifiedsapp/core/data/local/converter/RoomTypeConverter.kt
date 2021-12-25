package com.inhouse.classifiedsapp.core.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedTypeConverter
class RoomTypeConverter @Inject constructor(private val moshi: Moshi) {
    private fun <T> getAdapter(clazz: Class<T>): JsonAdapter<List<T>> {
        val listType = Types.newParameterizedType(List::class.java, clazz)
        return moshi.adapter(listType)
    }

    @TypeConverter
    fun fromListOfStrings(data: List<String>): String {
        return getAdapter(String::class.java).toJson(data)
    }

    @TypeConverter
    fun toListOfStrings(json: String): List<String>? {
        return getAdapter(String::class.java).fromJson(json)
    }
}