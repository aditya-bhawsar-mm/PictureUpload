package com.example.pictureupload.di.module

import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.AuthUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    internal fun providesAuthUseCase(): AuthUseCase {
        return AuthUseCaseImpl()
    }

}