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
fun TextView.setCreatedAtDate(createdAt: String?) {
    text = createdAt?.let {
        longDateFormat().format(dateFormatter().parse(createdAt) ?: Date())
    } ?: kotlin.run { longDateFormat().format(Date()) }

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

@BindingAdapter("imageFromUrls")
fun ImageView.setImageFromUrls(imageFromUrls: List<String>?) {
    imageFromUrls?.let {
        val imgUrl = imageFromUrls[0]
        if (imageFromUrls.isNotEmpty()) {
            load(imgUrl) {
                placeholder(R.drawable.loading_img)
                error(R.drawable.ic_broken_image)
            }
        } else {
            load(R.drawable.ic_broken_image)
        }
    } ?: kotlin.run {
        load(R.drawable.ic_broken_image)
    }
}
