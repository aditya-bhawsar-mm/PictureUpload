package com.example.pictureupload.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pictureupload.util.Constants

@Database(entities = [PicDetailsEntity::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class PicDatabase : RoomDatabase() {

    //Method To get Dao for pic details
    abstract fun getPicDao() : PicDetailsDao

}