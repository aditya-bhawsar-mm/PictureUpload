package com.example.pictureupload.framework.db

import com.example.pictureupload.data.PicSource
import com.example.pictureupload.domain.PicDetails
import kotlinx.coroutines.flow.Flow

class PicSourceImpl(private val picDao: PicDetailsDao) : PicSource{

    //Implementation Of Pic Dao for Insert
    override suspend fun insertPic(picDetails: PicDetails) {
        picDao.insertPicDetails(picDetails.toEntity())
    }

    //Implementation Of Pic Dao for Update
    override suspend fun updatePic(picDetails: PicDetails) {
        picDao.updatePicDetails(picDetails.toEntity())
    }

    //Implementation Of Pic Dao for Delete
    override suspend fun deletePic(picDetails: PicDetails) {
        picDao.deletePicDetails(picDetails.toEntity())
    }

    //Implementation of Pic Dap for deleting all items
    override suspend fun deleteAllPics() {
        picDao.deleteAllItems()
    }

    //Get Presence of path in db
    override fun getPicPresence(path: String): Flow<Int> {
        return picDao.getPicPresence(path = path)
    }

    //Get list of pics to upload from DB
    override suspend fun getToUploadPics(): List<PicDetails> {
        return picDao.listToUploadItems().map { picDetail -> picDetail.toDetails() }
    }


}