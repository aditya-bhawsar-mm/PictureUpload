package com.example.pictureupload.data

import com.example.pictureupload.domain.PicDetails
import kotlinx.coroutines.flow.Flow

class PicDbRepository(private val picSource: PicSource) {

    suspend fun insertPicDetails(picDetails: PicDetails) =
        picSource.insertPic(picDetails)

    suspend fun updatePicDetails(picDetails: PicDetails) =
        picSource.updatePic(picDetails)

    suspend fun deletePicDetails(picDetails: PicDetails) =
        picSource.deletePic(picDetails)

    suspend fun deleteAllPics() = picSource.deleteAllPics()

    suspend fun getToUploadPics(): List<PicDetails> =
        picSource.getToUploadPics()

    fun getPicPresence(path: String): Flow<Int> =
        picSource.getPicPresence(path)
}