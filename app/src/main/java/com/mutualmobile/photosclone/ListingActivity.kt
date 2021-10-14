package com.mutualmobile.photosclone

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.photosclone.ui.theme.PictureUploadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class ListingActivity : ComponentActivity() {

    private val viewModel: ListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListingScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.isUserLogged()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    @Composable
    fun ListingScreen() {
        PictureUploadTheme {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
                    .padding(5.dp)
            ) {

                val images = assets.list("img")
                if (images != null) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (img in images) {
                            ImageCard(image = img)
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No Images Were Found", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }

    @Composable
    fun ImageCard(image: String) {

        val inputStream = assets.open("img/$image")
        val bitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .shadow(5.dp),
            onClick = {
                val intent = Intent(this, PictureActivity::class.java)
                intent.putExtra("img", "img/$image")
                startActivity(intent)
            }
        ) {
            Image(
                bitmap = bitmap, contentDescription = "Image from assests",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ListingScreen()
    }
}
