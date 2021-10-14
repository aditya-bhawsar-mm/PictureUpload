package com.mutualmobile.photosclone.di.module

import com.mutualmobile.photosclone.data.AuthRepository
import com.mutualmobile.photosclone.data.PicDbRepository
import com.mutualmobile.photosclone.data.StorageRepository
import com.mutualmobile.photosclone.usecases.AuthUseCase
import com.mutualmobile.photosclone.usecases.AuthUseCaseImpl
import com.mutualmobile.photosclone.usecases.PicDbUseCase
import com.mutualmobile.photosclone.usecases.PicDbUseCaseImpl
import com.mutualmobile.photosclone.usecases.StorageUseCase
import com.mutualmobile.photosclone.usecases.StorageUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // Provides Implementation of AuthUseCase to Worker and View Models
    @Provides
    internal fun providesAuthUseCase(authRepo: AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(authRepo)
    }

    // Provides Implementation of PicDbUseCase to Worker and View Models
    @Provides
    internal fun providesPicDbUseCase(picDbRepository: PicDbRepository): PicDbUseCase {
        return PicDbUseCaseImpl(picDbRepository)
    }

    // Provides Implementation of StorageUseCase to Worker and View Models
    @Provides
    internal fun providesStorageUseCase(storageRepository: StorageRepository): StorageUseCase {
        return StorageUseCaseImpl(storageRepository)
    }
}
