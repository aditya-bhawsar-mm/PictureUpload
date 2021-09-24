package com.example.pictureupload.framework.db

import com.example.pictureupload.data.PicSource
import com.example.pictureupload.domain.PicDetails

class PicSourceImpl(private val picDao: PicDetailsDao) : PicSource{

    override suspend fun insertPic(picDetails: PicDetails) {
        val picEntity = PicDetailsEntity(picDetails.id, picDetails.path, picDetails.uploaded)
        picDao.insetPicDetails(picEntity)
    }

    override suspend fun updatePic(picDetails: PicDetails) {
        val picEntity = PicDetailsEntity(picDetails.id, picDetails.path, picDetails.uploaded)
        picDao.updatePicDetails(picEntity)
    }

    override suspend fun deletePic(picDetails: PicDetails) {
        val picEntity = PicDetailsEntity(picDetails.id, picDetails.path, picDetails.uploaded)
        picDao.deletePicDetails(picEntity)
    }
}