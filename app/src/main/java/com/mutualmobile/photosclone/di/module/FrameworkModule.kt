package com.mutualmobile.photosclone.di.module

import com.mutualmobile.photosclone.data.AuthProvider
import com.mutualmobile.photosclone.data.PicSource
import com.mutualmobile.photosclone.data.StorageProvider
import com.mutualmobile.photosclone.framework.db.PicDetailsDao
import com.mutualmobile.photosclone.framework.db.PicSourceImpl
import com.mutualmobile.photosclone.framework.firebase.AuthProviderImpl
import com.mutualmobile.photosclone.framework.firebase.StorageProviderImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    // Provides Auth Provider implementation to Repository
    @Provides
    @Singleton
    internal fun providesAuthProvider(): AuthProvider {
        return AuthProviderImpl(FirebaseAuth.getInstance())
    }

    // Provides Pic Source Implementation to the Repository
    @Provides
    @Singleton
    internal fun providesPicSource(picDetailsDao: PicDetailsDao): PicSource {
        return PicSourceImpl(picDetailsDao)
    }

    @Provides
    @Singleton
    internal fun providesStorageProvider(): StorageProvider {
        return StorageProviderImpl(FirebaseStorage.getInstance())
    }
}
