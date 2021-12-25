package com.inhouse.classifiedsapp.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inhouse.classifiedsapp.core.model.ClassifiedAd.Companion.TABLE_NAME
import com.squareup.moshi.Json

@Entity(tableName = TABLE_NAME)
data class ClassifiedAd(
    @PrimaryKey
    val uid: String,
    @Json(name = "created_at") val createdAt: String,
    val price: String,
    val name: String,
    @Json(name = "image_ids") val imageIds: List<String>,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "image_urls_thumbnails") val imageUrlsThumbnails: List<String>

) {
    companion object {
        const val TABLE_NAME = "classifieds_ad"
    }
}
