package com.mutualmobile.photosclone.data

import com.mutualmobile.photosclone.domain.PicDetails
import kotlinx.coroutines.flow.Flow

interface PicSource {

    suspend fun insertPic(picDetails: PicDetails)
    suspend fun updatePic(picDetails: PicDetails)
    suspend fun deletePic(picDetails: PicDetails)
    suspend fun deleteAllPics()
    suspend fun getToUploadPics(): List<PicDetails>
    fun getPicPresence(path: String): Flow<Int>
}
