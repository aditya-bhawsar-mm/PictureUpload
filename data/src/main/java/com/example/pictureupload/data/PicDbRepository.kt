package com.example.pictureupload.data

import com.example.pictureupload.domain.PicDetails

class PicDbRepository(private val picSource: PicSource) {

    suspend fun insertPicDetails(picDetails: PicDetails) =
        picSource.insertPic(picDetails)

    suspend fun updatePicDetails(picDetails: PicDetails) =
        picSource.updatePic(picDetails)

    suspend fun deletePicDetails(picDetails: PicDetails) =
        picSource.deletePic(picDetails)

    suspend fun deleteAllPics() =
        picSource.deleteAllPics()

    suspend fun getToUploadPics() =
        picSource.getToUploadPics()

    fun getPicPresence(path: String) =
        picSource.getPicPresence(path)
}