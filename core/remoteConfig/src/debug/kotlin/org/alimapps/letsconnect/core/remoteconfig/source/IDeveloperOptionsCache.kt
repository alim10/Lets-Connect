package org.alimapps.letsconnect.core.remoteconfig.source

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.remoteconfig.model.CacheCurlModel
import org.alimapps.letsconnect.core.remoteconfig.model.CacheMockLocationModel
import javax.inject.Singleton

@Singleton
interface IDeveloperOptionsCache {
    fun getCurlRequests(): Flow<List<CacheCurlModel>?>
    fun deleteCachedCurlRequests()
    suspend fun insertCacheCurlModel(curl: CacheCurlModel)

    fun getMockLocation(): Flow<CacheMockLocationModel?>
    fun deleteMockLocation()
    suspend fun insertMockLocation(location: CacheMockLocationModel)
}