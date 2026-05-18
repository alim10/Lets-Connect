package org.alimapps.letsconnect.core.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import org.alimapps.letsconnect.core.common.session.AppPreferenceV2
import org.alimapps.letsconnect.core.common.session.AppPrefsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AppPrefsModule {

    @Singleton
    @Binds
    abstract fun bindAppPrefs(appPrefs: AppPreferenceV2): AppPrefsRepository

}
