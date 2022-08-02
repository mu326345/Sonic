package com.yuyu.sonic

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("flightNum")
fun flightToNum(text: TextView, num: Int) {
    text.text = "Flight $num"
}

@BindingAdapter("toImage")
fun urlToImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("toDate")
fun transToDate(text: TextView, date: Long) {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
    val formateDate = formatter.format(Date(date * 1000))
    text.text = formateDate.toString()
}