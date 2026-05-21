package org.alimapps.letsconnect.core.network.source

import org.alimapps.letsconnect.core.network.db.DeveloperOptionsDatabase
import org.alimapps.letsconnect.core.network.model.CacheCurlModel
import org.alimapps.letsconnect.core.network.model.CacheMockLocationModel
import javax.inject.Inject

class RoomDeveloperOptionsCache
@Inject
constructor(
    appDatabase: DeveloperOptionsDatabase
) : IDeveloperOptionsCache {
    private val curlDao = appDatabase.curlRequestsDao()
    private val locationDao = appDatabase.mockLocationDao()
    override fun getCurlRequests() = curlDao.getCurlRequestList()
    override fun deleteCachedCurlRequests() = curlDao.deleteAllCurlRequest()
    override suspend fun insertCacheCurlModel(curl: CacheCurlModel) = curlDao.insertCurl(curl)
    override fun getMockLocation() = locationDao.getMockLocation()
    override fun deleteMockLocation() = locationDao.deleteMockLocation()
    override suspend fun insertMockLocation(location: CacheMockLocationModel) = locationDao.insertMockLocation(location)
}