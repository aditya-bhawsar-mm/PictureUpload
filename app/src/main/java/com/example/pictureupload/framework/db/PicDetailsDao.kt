package com.example.pictureupload.framework.db

import androidx.room.*

@Dao
interface PicDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetPicDetails(picDetailsEntity: PicDetailsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePicDetails(picDetailsEntity: PicDetailsEntity)

    @Delete
    fun deletePicDetails(picDetailsEntity: PicDetailsEntity)

}