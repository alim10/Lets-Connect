package com.alim.letsconnect.apps

import com.alim.letsconnect.core.navigations.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {

//    @Provides
//    @Singleton
//    fun provideProvider(): Navigator.Provider {
//        return DefaultNavigator()
//    }
}