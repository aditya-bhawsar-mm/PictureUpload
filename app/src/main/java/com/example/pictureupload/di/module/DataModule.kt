package com.example.pictureupload.di.module

import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.data.AuthRepository
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

}