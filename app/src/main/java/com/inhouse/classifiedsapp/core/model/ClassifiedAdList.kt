package com.inhouse.classifiedsapp.core.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassifiedAdList(val results: List<ClassifiedAd>)
