package com.inhouse.classifiedsapp.core.model

import com.inhouse.classifiedsapp.utils.dateFormatter
import com.squareup.moshi.Json
import java.util.*

data class ClassifiedAd(
    @Json(name = "created_at") val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    @Json(name = "image_ids") val imageIds: List<String>,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "image_urls_thumbnails") val imageUrlsThumbnails: List<String>
)

fun ClassifiedAd.createdAtDate(): Date = dateFormatter().parse(createdAt) ?: Date()