package com.inhouse.classifiedsapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateFormatter() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.getDefault())

fun longDateFormat() = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())

fun dateOnlyFormat() = SimpleDateFormat("EEE dd MMM", Locale.getDefault())

fun timeOnlyFormat() = SimpleDateFormat("hh:mm a", Locale.getDefault())
