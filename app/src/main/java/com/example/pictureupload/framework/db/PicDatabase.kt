package com.example.pictureupload.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pictureupload.util.Constants

@Database(entities = [PicDetailsEntity::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class PicDatabase : RoomDatabase() {

    abstract fun getPicDao() : PicDetailsDao

}