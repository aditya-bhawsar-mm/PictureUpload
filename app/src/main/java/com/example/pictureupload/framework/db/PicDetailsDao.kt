package com.example.pictureupload.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PicDetailsDao {

    //Pic Details Insert Method Implemented by the @Dao annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicDetails(picDetailsEntity: PicDetailsEntity)

    //Pic Details Update Method Implemented by the @Dao annotation
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePicDetails(picDetailsEntity: PicDetailsEntity)

    //Pic Details Delete Method Implemented by the @Dao annotation
    @Delete
    suspend fun deletePicDetails(picDetailsEntity: PicDetailsEntity)

    @Query("SELECT COUNT(*) FROM pic_tb WHERE path = :path")
    fun getPicPresence(path: String): Flow<Int>

    @Query("DELETE FROM pic_tb")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM pic_tb WHERE uploaded =:value")
    suspend fun listToUploadItems(value: Boolean = false) : List<PicDetailsEntity>
}