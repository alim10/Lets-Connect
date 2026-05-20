package org.alimapps.letsconnect.core.analytics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AnalyticsModuleFirebase {
    @Provides
    @Singleton
    fun bindAnalytics(): Analytics = FirebaseAnalytics()
}