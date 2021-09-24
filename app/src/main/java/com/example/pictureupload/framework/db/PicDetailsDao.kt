package com.example.pictureupload.framework.db

import androidx.room.*

@Dao
interface PicDetailsDao {

    //@Query("SELECT * FROM pic_tb")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetPicDetails(picDetailsEntity: PicDetailsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePicDetails(picDetailsEntity: PicDetailsEntity)

    @Delete
    suspend fun deletePicDetails(picDetailsEntity: PicDetailsEntity)

}