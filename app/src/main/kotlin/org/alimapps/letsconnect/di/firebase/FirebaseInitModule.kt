package org.alimapps.letsconnect.di.firebase

import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseInitModule {

    @Provides
    @Singleton
    fun provideFirebaseMessagingInstance(initializer: FirebaseInitializer): FirebaseMessaging {
        initializer.initialize()
        return FirebaseMessaging.getInstance()
    }
}