package com.example.pictureupload.domain

//Domain Class for Pic Details
data class PicDetails(
    val id: Int,
    val path: String,
    val uploaded: Boolean
)