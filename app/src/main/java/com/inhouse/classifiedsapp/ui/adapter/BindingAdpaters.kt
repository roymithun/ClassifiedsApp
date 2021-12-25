package com.inhouse.classifiedsapp.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.inhouse.classifiedsapp.R
import com.inhouse.classifiedsapp.utils.dateFormatter
import com.inhouse.classifiedsapp.utils.longDateFormat
import java.util.*

@BindingAdapter("createdAtDate")
fun TextView.setCreateAtDate(createdAt: String) {
    text = longDateFormat().format(dateFormatter().parse(createdAt) ?: Date())
}

@BindingAdapter("thumbnailFromUrls")
fun ImageView.setThumbnailFromUrls(imageUrlsThumbnails: List<String>) {
    if (imageUrlsThumbnails.isNotEmpty()) {
        val thumbnailImgUrl = imageUrlsThumbnails[0]
        load(thumbnailImgUrl) {
            placeholder(R.drawable.loading_img)
            error(R.drawable.ic_broken_image)
            transformations(CircleCropTransformation())
        }
    } else {
        load(R.drawable.ic_broken_image)
    }
}