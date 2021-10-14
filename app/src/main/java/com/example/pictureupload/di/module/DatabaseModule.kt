package com.example.pictureupload.di.module

import android.content.Context
import androidx.room.Room
import com.example.pictureupload.framework.db.PicDatabase
import com.example.pictureupload.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provides the Database to the methods requiring it
    @Provides
    @Singleton
    internal fun providesDatabase(@ApplicationContext ctx: Context): PicDatabase {
        return Room.databaseBuilder(
            ctx, PicDatabase::class.java, Constants.DB_NAME
        ).build()
    }

    // Provides Dao object to the Source Implementation
    @Provides
    @Singleton
    internal fun providesPicDao(picDatabase: PicDatabase) = picDatabase.getPicDao()
}
