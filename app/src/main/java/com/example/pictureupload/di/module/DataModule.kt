package com.example.pictureupload.di.module

import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.data.AuthRepository
import com.example.pictureupload.data.PicDbRepository
import com.example.pictureupload.data.PicSource
import com.example.pictureupload.data.StorageProvider
import com.example.pictureupload.data.StorageRepository
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
    internal fun providesPicRepos(picSource: PicSource): PicDbRepository{
        return PicDbRepository(picSource)
    }

    @Provides
    @Singleton
    internal fun providesStorageRepo(storageProvider: StorageProvider): StorageRepository{
        return StorageRepository(storageProvider)
    }

}
