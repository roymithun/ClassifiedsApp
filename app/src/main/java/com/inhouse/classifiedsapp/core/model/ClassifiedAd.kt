package com.inhouse.classifiedsapp.core.model

import com.squareup.moshi.Json

data class ClassifiedAd(
    @Json(name = "created_at") val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    @Json(name = "image_ids") val imageIds: List<String>,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "image_urls_thumbnails") val imageUrlsThumbnails: List<String>
)