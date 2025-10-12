package com.alim.letsconnect.di.firebase

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FirebaseInitializerEntryPoint {

    fun getFirebaseInitializer(): FirebaseInitializer
}
