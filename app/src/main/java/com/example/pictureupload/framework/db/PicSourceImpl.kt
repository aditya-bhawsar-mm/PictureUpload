package com.example.pictureupload.framework.db

import com.example.pictureupload.data.PicSource
import com.example.pictureupload.domain.PicDetails

class PicSourceImpl(private val picDao: PicDetailsDao) : PicSource{

    override suspend fun insertPic(picDetails: PicDetails) {
        picDao.insetPicDetails(picDetails.toEntity())
    }

    override suspend fun updatePic(picDetails: PicDetails) {
        picDao.updatePicDetails(picDetails.toEntity())
    }

    override suspend fun deletePic(picDetails: PicDetails) {
        picDao.deletePicDetails(picDetails.toEntity())
    }
}