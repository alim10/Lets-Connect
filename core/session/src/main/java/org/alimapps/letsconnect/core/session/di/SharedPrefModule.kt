package org.alimapps.letsconnect.core.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.session.SharedPrefsRepositoryImpl



//@Module
//@InstallIn(SingletonComponent::class)
//object SharedPrefModule {
//
//    @Provides
//    fun provideAppPreference(@ApplicationContext context: Context): AppPreference {
//        return AppPreference(context)
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppPrefsModule {

    @Singleton
    @Binds
    abstract fun bindAppPrefs(appPrefs: SharedPrefsRepositoryImpl): SharedPrefsRepository

}
