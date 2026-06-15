package org.alimapps.letsconnect.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }

    @Provides
    @Singleton
    @Named(RemoteConfigSource.DEFAULT_FLAGS_ARRAY)
    fun provideFirebaseRemoteConfigDefaultArray(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }

    @Provides
    @Singleton
    @Named(RemoteConfigSource.DEFAULT_FLAGS)
    fun provideFirebaseRemoteConfigDefault(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseMessagingInstance(): FirebaseMessaging {
        return FirebaseMessaging.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAnalyticsInstance(
        @ApplicationContext context: Context,
    ): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseCrashlyticsInstance(
    ): FirebaseCrashlytics {
        return FirebaseCrashlytics.getInstance()
    }
}
