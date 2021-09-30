package com.example.pictureupload.data

import com.example.pictureupload.domain.PicDetails

interface PicSource {

    suspend fun insertPic(picDetails: PicDetails)
    suspend fun updatePic(picDetails: PicDetails)
    suspend fun deletePic(picDetails: PicDetails)

}