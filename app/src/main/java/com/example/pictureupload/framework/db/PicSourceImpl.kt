package com.example.pictureupload.framework.db

import com.example.pictureupload.data.PicSource
import com.example.pictureupload.domain.PicDetails

class PicSourceImpl(private val picDao: PicDetailsDao) : PicSource{

    //Implementation Of Pic Dao for Insert
    override suspend fun insertPic(picDetails: PicDetails) {
        picDao.insetPicDetails(picDetails.toEntity())
    }

    //Implementation Of Pic Dao for Update
    override suspend fun updatePic(picDetails: PicDetails) {
        picDao.updatePicDetails(picDetails.toEntity())
    }

    //Implementation Of Pic Dao for Delete
    override suspend fun deletePic(picDetails: PicDetails) {
        picDao.deletePicDetails(picDetails.toEntity())
    }
}