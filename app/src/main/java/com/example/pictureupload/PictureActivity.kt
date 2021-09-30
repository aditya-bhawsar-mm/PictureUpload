package com.example.pictureupload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pictureupload.ui.theme.PictureUploadTheme

class PictureActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PictureScreen()
        }
    }

    @Composable
    fun PictureScreen(){
        PictureUploadTheme {

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PictureScreen()
    }
}
