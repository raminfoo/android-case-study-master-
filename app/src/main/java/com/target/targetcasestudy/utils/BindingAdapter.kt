package com.target.targetcasestudy.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout
import com.target.targetcasestudy.R

//Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    Glide.with(imageView.context)
        .load(imgUrl)
        .into(imageView)
}


//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isNetworkError", "dealsList")
fun isNetworkError(view: View, isNetWorkError: Boolean, dealsList: Any?) {
    view.visibility = if (dealsList != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

@BindingAdapter("vis", "dealsList")
fun vis(view: View, isNetWorkError: Boolean, dealsList: Any?) {
    view.visibility = View.GONE
    if (isNetWorkError) {
        view.visibility = View.VISIBLE
    }
}

//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isShimmerVisible", "dealsList")
fun bindShimmerAnimation(view: ShimmerFrameLayout, isNetWorkError: Boolean, dealsList: Any?) {
    if (dealsList != null || isNetWorkError) {
        view.visibility = View.GONE
        view.run { stopShimmerAnimation() }
    } else {
        view.visibility = View.VISIBLE
        view.run { startShimmerAnimation() }
    }
}
