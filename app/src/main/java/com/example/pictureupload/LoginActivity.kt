package com.example.pictureupload

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pictureupload.ui.theme.Grey
import com.example.pictureupload.ui.theme.PictureUploadTheme
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var alertDialog: androidx.appcompat.app.AlertDialog
    private lateinit var scaffoldState: ScaffoldState

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }

    @ExperimentalMaterialApi
    override fun onStart() {
        super.onStart()
        checkUserLogin()
    }

    @ExperimentalMaterialApi
    private fun checkUserLogin(){
        if(viewModel.isUserLogged()){
            val intent  = Intent(this, ListingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @ExperimentalMaterialApi
    @Composable
    @ExperimentalMaterialApi
    fun LoginScreen(){
        alertDialog = MaterialAlertDialogBuilder(this).create()

        scaffoldState = rememberScaffoldState()
        var emailState by rememberSaveable { mutableStateOf("") }
        var passwordState by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        if(viewModel.viewState.hasObservers()) viewModel.viewState.removeObservers(this)
        viewModel.viewState.observe(this){ viewState->
            when(viewState){
                is LoginViewState.Idle->{}
                is LoginViewState.Loading->{
                    if(alertDialog.isShowing){alertDialog.dismiss()}
                    alertDialog.setCancelable(false)
                    alertDialog.setTitle("Processing")
                    alertDialog.setMessage("Loading your request please wait")
                    alertDialog.setIcon(R.mipmap.ic_launcher_round)
                    alertDialog.show()
                }
                is LoginViewState.Success->{
                    if(alertDialog.isShowing){alertDialog.dismiss()}
                    checkUserLogin()
                }
                is LoginViewState.Error->{
                    if(alertDialog.isShowing){alertDialog.dismiss()}
                    alertDialog.setCancelable(true)
                    alertDialog.setTitle("Error Received")
                    alertDialog.setMessage(viewState.msg)
                    alertDialog.setIcon(R.mipmap.ic_launcher_round)
                    alertDialog.show()
                    viewModel.resetViewState()
                }
            }
        }

        PictureUploadTheme {
            Column(modifier = Modifier.background(color = Color.White)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f)
                    .clip(shape = RoundedCornerShape(bottomEnd = 80.dp))
                    .background(color = Grey)
                ){

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
                    .background(color = Grey)) {

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(topStart = 80.dp))
                        .fillMaxWidth()
                        .background(color = Color.White)
                    ) {

                        Column() {
                            Text(text = "Login with Email", modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp), textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))

                            OutlinedTextField(value = emailState, onValueChange = {emailState = it},
                                label = { Text(text = "Email")},
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedLabelColor = Color.Blue,
                                    unfocusedBorderColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                            )

                            OutlinedTextField(value = passwordState, onValueChange = {passwordState = it},
                                label = { Text(text = "Password")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    val image = if(passwordVisibility) Icons.Filled.VisibilityOff
                                    else Icons.Filled.Visibility

                                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                        Icon(imageVector  = image, "", tint = Color.Gray)
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedLabelColor = Color.Blue,
                                    unfocusedBorderColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                            )

                            Button(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(20.dp)),
                                onClick = {
                                    viewModel.validateInputAndLogIn(emailState, passwordState)
                                }) {
                                Text(text = "Login", style = TextStyle(fontSize = 16.sp))
                            }

                            Text(text = "|OR|", modifier = Modifier
                                .fillMaxWidth(), textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Black))

                            Button(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(20.dp)),
                                onClick = {}) {
                                Text(text = "Login with Social", style = TextStyle(fontSize = 16.sp))
                            }

                            Button(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(20.dp)),
                                onClick = {
                                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                                    startActivity(intent)
                                }) {
                                Text(text = "Register", style = TextStyle(fontSize = 16.sp))
                            }

                        }
                    }

                }
            }
        }

    }

    @ExperimentalMaterialApi
    @Preview(showBackground = true)
    @Composable
    @ExperimentalMaterialApi
    fun DefaultPreview() {
        LoginScreen()
    }
}
