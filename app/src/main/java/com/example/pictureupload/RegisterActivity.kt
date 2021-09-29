package com.example.pictureupload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pictureupload.ui.theme.PictureUploadTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PictureUploadTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen(){
    Column(modifier = Modifier.background(color = colorResource(id = R.color.white))) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.4f)
            .clip(shape = RoundedCornerShape(bottomEnd = 80.dp))
            .background(color = colorResource(id = R.color.grey))
        ) {
            Image(
                painterResource(id = R.drawable.ic_launcher_background) , contentDescription = "App icon"
                ,modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(25.dp)
            )
        }

        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.grey))) {
            
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(topStart = 80.dp))
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.white))) {
                
                Column {
                    Text(text = "Register with email", textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )

                    OutlinedTextField(value = "", onValueChange = {},
                        label = { Text(text = "Email")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
                    )
                    OutlinedTextField(value = "", onValueChange = {},
                        label = { Text(text = "Password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( start = 16.dp, end = 16.dp, bottom = 4.dp)
                    )
                    OutlinedTextField(value = "", onValueChange = {},
                        label = { Text(text = "Confirm Password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( start = 16.dp, end = 16.dp, bottom = 4.dp)
                    )
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp)),
                        onClick = {}) {
                        Text(text = "Register", style = TextStyle(fontSize = 18.sp))
                    }
                }
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PictureUploadTheme {
        RegisterScreen()
    }
}