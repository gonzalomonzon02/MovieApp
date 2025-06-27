package com.gonzalomonzon02.movieapp.presentation.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.gonzalomonzon02.movieapp.BuildConfig
import com.gonzalomonzon02.movieapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageURLView(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes placeholderDrawableRes: Int = R.drawable.ic_placeholder_image,
    @DrawableRes errorDrawableRes: Int = R.drawable.ic_error_image
) {
    if (url.isNotBlank()) {
        val imageModel = "${BuildConfig.IMAGE_URL}$url"
        GlideImage(
            model = imageModel,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier,
            loading = placeholder(placeholderDrawableRes),
            failure = placeholder(errorDrawableRes),
        )
    } else {
        Image(
            painter = painterResource(placeholderDrawableRes),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
