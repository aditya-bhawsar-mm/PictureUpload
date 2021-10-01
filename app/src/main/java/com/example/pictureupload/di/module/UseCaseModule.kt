package com.example.pictureupload.di.module

import com.example.pictureupload.data.AuthRepository
import com.example.pictureupload.data.PicDbRepository
import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.AuthUseCaseImpl
import com.example.pictureupload.usecases.PicDbUseCase
import com.example.pictureupload.usecases.PicDbUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    //Provides Implementation of AuthUseCase for View Model
    @Provides
    internal fun providesAuthUseCase(authRepo: AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(authRepo)
    }

    //Provides Implementation of PicDbUseCase to Worker and View Model
    @Provides
    internal fun providesPicDbUseCase(picDbRepository: PicDbRepository): PicDbUseCase{
        return PicDbUseCaseImpl(picDbRepository)
    }
}
