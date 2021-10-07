package com.example.pictureupload

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pictureupload.domain.PicDetails
import com.example.pictureupload.ui.theme.PictureUploadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureActivity : ComponentActivity() {

    private val viewModel: PictureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val path = intent.getStringExtra("img")?:""
            PictureScreen(path)
        }
    }

    @Composable
    fun PictureScreen(img: String){
        PictureUploadTheme {
            Column(modifier = Modifier
                .background(color = Color.Black)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val inputStream = assets.open(img)
                val bitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()

                Image(bitmap = bitmap, contentDescription = ""
                    ,modifier = Modifier.fillMaxWidth()
                    ,contentScale = ContentScale.Crop
                )
            }

            val picPresence = viewModel.getPathPresence(img).collectAsState(initial = 0)
            UploadButton(picPresence, img)
        }
    }

    @Composable
    fun UploadButton(picPresence: State<Int>, path: String){
        if(picPresence.value == 0){
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    viewModel.insertPicDetails(
                        PicDetails(0,path,false), this@PictureActivity
                    )
                }) {
                    Text(text = "Upload to Cloud")
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PictureScreen("img/img2.jpeg")
    }
}
