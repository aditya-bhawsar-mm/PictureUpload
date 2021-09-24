package com.example.pictureupload.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pictureupload.framework.db.PicDatabase.Companion.DB_VERSION

@Database(entities = [PicDetailsEntity::class], version = DB_VERSION, exportSchema = false)
abstract class PicDatabase : RoomDatabase() {

    abstract fun getPicDao() : PicDetailsDao

    companion object{
        const val DB_NAME ="pic_details_db"
        const val DB_VERSION = 1
    }

}