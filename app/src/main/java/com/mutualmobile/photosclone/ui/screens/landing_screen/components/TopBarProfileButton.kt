package com.mutualmobile.photosclone.ui.screens.landing_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.mutualmobile.photosclone.R
import com.mutualmobile.photosclone.util.shortToast

@ExperimentalCoilApi
@Composable
fun TopBarProfileButton(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Card(
        shape = CircleShape,
        modifier = modifier
            .size(32.dp)
            .offset(x = (-16).dp)
    ) {

        val painter = rememberImagePainter(
            data = "https://cdn.pixabay.com/photo/2020/03/12/22/26/drops-4926381_1280.jpg",
            builder = {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_person)
                crossfade(500)
                error(R.drawable.ic_error)
                size(32, 32)
            }
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier
                .clickable {
                    context.shortToast("TODO: Need to make this dialog!")
                }
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> CircularProgressIndicator(strokeWidth = 3.dp)
            else -> Unit
        }
    }
}
