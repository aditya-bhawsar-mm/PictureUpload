package com.example.pictureupload.di.module

import com.example.pictureupload.data.PicSource
import com.example.pictureupload.framework.db.PicDetailsDao
import com.example.pictureupload.framework.db.PicSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PicSourceModule {

    //Provides Pic Source Implementation to the Repository
    @Provides
    @Singleton
    internal fun providesPicSource(picDetailsDao: PicDetailsDao): PicSource{
        return PicSourceImpl(picDetailsDao)
    }

}