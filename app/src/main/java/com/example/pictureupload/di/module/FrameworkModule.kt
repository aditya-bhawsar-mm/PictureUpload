package com.example.pictureupload.di.module

import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.framework.firebase.AuthProviderImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    @Provides
    @Singleton
    internal fun providesAuthProvider(): AuthProvider{
        return AuthProviderImpl(FirebaseAuth.getInstance())
    }

}