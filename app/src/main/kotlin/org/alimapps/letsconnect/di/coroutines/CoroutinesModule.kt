package org.alimapps.letsconnect.di.coroutines
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CoroutinesModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider):
        DispatchersProvider

    companion object {
        @DefaultDispatcher
        @Provides
        fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @IoDispatcher
        @Provides
        fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @MainDispatcher
        @Provides
        fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @ApplicationScope
        @Singleton
        @Provides
        fun providesApplicationScope(
            @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
        ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
    }
}