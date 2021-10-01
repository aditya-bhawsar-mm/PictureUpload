package com.example.pictureupload.usecases

import com.example.pictureupload.data.PicDbRepository
import com.example.pictureupload.domain.PicDetails
import kotlinx.coroutines.flow.Flow

interface PicDbUseCase {

    suspend fun insertPicDetails(picDetails: PicDetails)
    suspend fun updatePicDetails(picDetails: PicDetails)
    suspend fun deletePicDetails(picDetails: PicDetails)
    suspend fun deleteAllPics()
    suspend fun getToUploadPics(): List<PicDetails>
    fun getPicPresence(path: String): Flow<Int>

}

class PicDbUseCaseImpl(private val picRepo: PicDbRepository): PicDbUseCase{
    override suspend fun insertPicDetails(picDetails: PicDetails) {
        picRepo.insertPicDetails(picDetails)
    }

    override suspend fun updatePicDetails(picDetails: PicDetails) {
        picRepo.updatePicDetails(picDetails)
    }

    override suspend fun deletePicDetails(picDetails: PicDetails) {
        picRepo.deletePicDetails(picDetails)
    }

    override suspend fun deleteAllPics() {
        picRepo.deleteAllPics()
    }

    override suspend fun getToUploadPics(): List<PicDetails> {
        return picRepo.getToUploadPics()
    }

    override fun getPicPresence(path: String): Flow<Int> {
        return picRepo.getPicPresence(path)
    }

}