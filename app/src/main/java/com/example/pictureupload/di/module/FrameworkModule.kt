package com.example.pictureupload.di.module

import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.data.PicSource
import com.example.pictureupload.data.StorageProvider
import com.example.pictureupload.framework.db.PicDetailsDao
import com.example.pictureupload.framework.db.PicSourceImpl
import com.example.pictureupload.framework.firebase.AuthProviderImpl
import com.example.pictureupload.framework.firebase.StorageProviderImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    //Provides Auth Provider implementation to Repository
    @Provides
    @Singleton
    internal fun providesAuthProvider(): AuthProvider {
        return AuthProviderImpl(FirebaseAuth.getInstance())
    }

    //Provides Pic Source Implementation to the Repository
    @Provides
    @Singleton
    internal fun providesPicSource(picDetailsDao: PicDetailsDao): PicSource{
        return PicSourceImpl(picDetailsDao)
    }

    @Provides
    @Singleton
    internal fun providesStorageProvider(): StorageProvider{
        return StorageProviderImpl(FirebaseStorage.getInstance())
    }
}
