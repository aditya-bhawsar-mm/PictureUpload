package com.example.pictureupload

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    lateinit var alertDialog: androidx.appcompat.app.AlertDialog
    lateinit var scaffoldState: ScaffoldState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegisterScreen()
        }
    }


    @Composable
    fun RegisterScreen(){

        alertDialog = MaterialAlertDialogBuilder(this).create()

        scaffoldState = rememberScaffoldState()
        var emailState by rememberSaveable { mutableStateOf("") }
        var passwordState by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        var passwordConfirmVisibility by remember { mutableStateOf(false) }
        var passwordConfirmState by rememberSaveable { mutableStateOf("") }


        if(viewModel.viewState.hasObservers()) viewModel.viewState.removeObservers(this)
        viewModel.viewState.observe(this){ viewState->
            when(viewState){
                is RegisterViewState.Idle->{}
                is RegisterViewState.Loading->{
                    if(alertDialog.isShowing){alertDialog.dismiss()}
                    alertDialog.setCancelable(false)
                    alertDialog.setTitle("Processing")
                    alertDialog.setMessage("Loading your request please wait")
                    alertDialog.setIcon(R.mipmap.ic_launcher_round)
                    alertDialog.show()
                }
                is RegisterViewState.Success->{
                    if(alertDialog.isShowing){alertDialog.dismiss()}
                    Toast.makeText(
                        this,
                        "Registered Successfully!!!",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
                is RegisterViewState.Error->{
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
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {

                Column(modifier = Modifier.background(color = Color.White))
                {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.4f)
                        .clip(shape = RoundedCornerShape(bottomEnd = 80.dp))
                        .background(color = Color.White)
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_launcher_background) ,
                            contentDescription = "App icon",
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(25.dp)
                        )
                    }

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(color = Color.White)) {

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(topStart = 80.dp))
                            .fillMaxHeight()
                            .background(color = Grey)) {

                            Column {
                                Text(text = "Register with email", textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                )

                                OutlinedTextField(value = emailState, onValueChange = { emailState = it },
                                    label = { Text(text = "Email")},
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 10.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 4.dp
                                        ),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        unfocusedBorderColor = Color.Blue,
                                        unfocusedLabelColor = Color.Blue
                                    )
                                )
                                OutlinedTextField(
                                    value =passwordState,
                                    onValueChange = {passwordState = it},
                                    label = { Text(text = "Password")},
                                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        val image = if (passwordVisibility)
                                            Icons.Filled.VisibilityOff
                                        else Icons.Filled.Visibility

                                        IconButton(onClick = {
                                            passwordVisibility= !passwordVisibility
                                        }) {
                                            Icon(imageVector  = image, "", tint = Color.Gray)
                                        }
                                    },
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),

                                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Blue,unfocusedLabelColor = Color.Blue
                                    )
                                )
                                OutlinedTextField(value = passwordConfirmState, onValueChange = {passwordConfirmState = it},
                                    label = { Text(text = "Confirm Password")},
                                    singleLine = true,
                                    visualTransformation = if (passwordConfirmVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        val image = if (passwordConfirmVisibility)
                                            Icons.Filled.VisibilityOff
                                        else Icons.Filled.Visibility

                                        IconButton(onClick = {
                                            passwordConfirmVisibility= !passwordConfirmVisibility
                                        }) {
                                            Icon(imageVector  = image, "",tint = Color.Gray)
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
                                        unfocusedBorderColor = Color.Blue
                                        ,unfocusedLabelColor = Color.Blue
                                    )
                                )
                                Button(
                                    modifier = Modifier
                                        .padding(24.dp)
                                        .fillMaxWidth()
                                        .clip(shape = RoundedCornerShape(20.dp)),
                                    onClick = { viewModel.validateInputAndRegister(emailState, passwordState, passwordConfirmState) }) {
                                    Text(text = "Register", style = TextStyle(fontSize = 18.sp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RegisterScreen()
    }


}

