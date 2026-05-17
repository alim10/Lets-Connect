package org.alimapps.letsconnect.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(app: FirebaseApp): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance(app)
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
