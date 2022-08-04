package com.yuyu.sonic

import android.util.Log
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
fun transToDate(textView: TextView, date: Long) {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").apply {
        timeZone = TimeZone.getTimeZone("Asia/Taipei")
    }
    val formateDate = formatter.format(Date(date * 1000))
    textView.text = formateDate.toString()
}

@BindingAdapter("reused")
fun isReused(textView: TextView, isReused: Boolean?) {
    if (isReused == null) {
        textView.text = textView.context.getString(R.string.no_info)
    } else {
        if (isReused) {
            textView.text = textView.context.getString(R.string.yes)
        } else {
            textView.text = textView.context.getString(R.string.no)
        }
    }
}

@BindingAdapter("landing")
fun landing(textView: TextView, isLanding: Boolean?) {
    if(isLanding == null) {
        textView.text = textView.context.getString(R.string.no_info)
    } else {
        if (isLanding) {
            textView.text = textView.context.getString(R.string.yes)
        } else {
            textView.text = textView.context.getString(R.string.no)
        }
    }
}