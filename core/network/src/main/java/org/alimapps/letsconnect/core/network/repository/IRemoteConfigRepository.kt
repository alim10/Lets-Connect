package org.alimapps.letsconnect.core.network.repository

import kotlinx.coroutines.flow.Flow

interface IRemoteConfigRepository {
    
    fun initRemoteConfigDefaultFlags(): Flow<Boolean>
    fun initRemoteConfig(): Flow<Boolean>
    fun getFeatureDefaultsListKey(): Boolean
    
    fun getDailyActivityKey(): Boolean
    fun getStepsAchievementsKey(): Boolean
    fun getDailyActivityCampaignKey(): Boolean
    
    fun get937ComplaintsKey(): Boolean
    fun get937AddComplaintKey(): Boolean
    
    // View partners page
    fun getPartnerPageKey(): Boolean

    // Network related flags
    fun getRefreshTokenExpirationFeatureKey(): Boolean
    fun getRefreshTokenFeatureKey(): String
}