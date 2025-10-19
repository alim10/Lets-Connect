package com.alim.letsconnect.core.remoteconfig.repository

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
    
}