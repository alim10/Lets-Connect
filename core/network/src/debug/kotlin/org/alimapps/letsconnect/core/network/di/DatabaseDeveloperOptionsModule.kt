package org.alimapps.letsconnect.core.network.di
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import org.alimapps.letsconnect.core.common.di.coroutines.IoDispatcher
import org.alimapps.letsconnect.core.network.db.DeveloperOptionsDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseDeveloperOptionsModule {
    @Singleton
    @Provides
    fun provideDeveloperDatabase(@ApplicationContext context: Context): DeveloperOptionsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DeveloperOptionsDatabase::class.java,
            DeveloperOptionsDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
//
//    @Singleton
//    @Provides
//    fun provideCacheRemoteConfig(
//        remoteConfigSource: IRemoteConfigSource,
//        db: DeveloperOptionsDatabase,
//        @IoDispatcher io: CoroutineDispatcher
//    ) = CacheRemoteConfigRepositoryImpl(
//        remoteConfigSource = remoteConfigSource,
//        db,
//        io
//    )
}