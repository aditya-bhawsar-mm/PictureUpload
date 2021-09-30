package com.example.pictureupload.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface PicDetailsDao {

    //Pic Details Insert Method Implemented by the @Dao annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetPicDetails(picDetailsEntity: PicDetailsEntity)

    //Pic Details Update Method Implemented by the @Dao annotation
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePicDetails(picDetailsEntity: PicDetailsEntity)

    //Pic Details Delete Method Implemented by the @Dao annotation
    @Delete
    suspend fun deletePicDetails(picDetailsEntity: PicDetailsEntity)

}