package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pictureupload.util.isFirstItemVisible

@Composable
fun SharingScreen(
  modifier: Modifier = Modifier,
  topBarHeight: Dp,
  changeTopBarColor: (changeColor: Boolean) -> Unit
) {
  val listState = rememberLazyListState()
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    LazyColumn(state = listState, contentPadding = PaddingValues(top = topBarHeight)) {
      items(100) { index ->
        Text(
          "I'm item $index", modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
        )
      }
    }
    changeTopBarColor(listState.isFirstItemVisible())
  }
}