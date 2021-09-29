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

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PictureUploadTheme {
                // A surface container using the 'background' color from the theme
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(){
    Column(modifier = Modifier.background(color = colorResource(id = R.color.white))) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.4f)
            .clip(shape = RoundedCornerShape(bottomEnd = 100.dp))
            .background(color = colorResource(id = R.color.grey))){

            Image(painterResource(id = R.drawable.ic_launcher_background) , contentDescription = "App icon"
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
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(topStart = 100.dp))
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.white))
            ) {

                Column() {
                    Text(text = "Login", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))

                    OutlinedTextField(value = "", onValueChange = {},
                        label = { Text(text = "Email")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp))

                    OutlinedTextField(value = "", onValueChange = {},
                        label = { Text(text = "Password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp))

                    Text(text = "Forgot Password?", modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp), textAlign = TextAlign.End,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Black))

                    Button(
                        modifier = Modifier
                            .padding(14.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp)),
                        onClick = {}) {
                        Text(text = "Login", style = TextStyle(fontSize = 16.sp))
                    }

                    Button(
                        modifier = Modifier
                            .padding(14.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp)),
                        onClick = {}) {
                        Text(text = "Login with Social", style = TextStyle(fontSize = 16.sp))
                    }

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PictureUploadTheme {
        LoginScreen()
    }
}