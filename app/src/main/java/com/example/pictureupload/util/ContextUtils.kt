package com.example.pictureupload.util

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState

fun Context.shortToast(msg: String) {
  Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun LazyListState.isFirstItemVisible() = layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0