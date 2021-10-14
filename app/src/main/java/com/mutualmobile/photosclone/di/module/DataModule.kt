package com.mutualmobile.photosclone.di.module

import com.mutualmobile.photosclone.data.AuthProvider
import com.mutualmobile.photosclone.data.AuthRepository
import com.mutualmobile.photosclone.data.PicDbRepository
import com.mutualmobile.photosclone.data.PicSource
import com.mutualmobile.photosclone.data.StorageProvider
import com.mutualmobile.photosclone.data.StorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun providesAuthRepo(authProvider: AuthProvider): AuthRepository {
        return AuthRepository(authProvider)
    }

    @Provides
    @Singleton
    internal fun providesPicRepos(picSource: PicSource): PicDbRepository {
        return PicDbRepository(picSource)
    }

    @Provides
    @Singleton
    internal fun providesStorageRepo(storageProvider: StorageProvider): StorageRepository {
        return StorageRepository(storageProvider)
    }
}
