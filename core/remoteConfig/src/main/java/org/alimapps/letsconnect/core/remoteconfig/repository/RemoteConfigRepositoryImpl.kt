package org.alimapps.letsconnect.core.remoteconfig.repository

import org.alimapps.letsconnect.core.network.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_ACHIEVEMENTS
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_CAMPAIGNS
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.Dashboard.DASHBOARD_DAILY_ACTIVITY
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.Network.REFRESH_TOKEN_EXPIRATION_FEATURE
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.Network.REFRESH_TOKEN_FEATURE
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.PartnersPage.PARTNERS_PAGE
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_ADD_COMPLAINT
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_COMPLAINTS
import org.alimapps.letsconnect.core.remoteconfig.db.DeveloperOptionsDatabase


import javax.inject.Inject

class RemoteConfigRepositoryImpl
@Inject
constructor(
    private val remoteConfigSource: IRemoteConfigSource,
    db: DeveloperOptionsDatabase,
): IRemoteConfigRepository {

    private val dao = db.featureFlagsDao()

    override fun initRemoteConfigDefaultFlags() = remoteConfigSource.initRemoteConfigDefaultFlags()

    override fun initRemoteConfig() = remoteConfigSource.initRemoteConfig()

    private fun getConfigurationValue(key: String) = runCatching { dao.getFeatureFlags(key).isEnable }.getOrNull() ?: true

    private fun getConfigurationData(key: String) = runCatching { dao.getFeatureFlags(key).data }.getOrNull() ?: ""

    private fun getConfigurationString(key: String) = runCatching { dao.getFeatureFlags(key).data as String }.getOrNull() ?: ""


    override fun getFeatureDefaultsListKey() = getConfigurationValue(org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.FEATURE_DEFAULTS_LIST)
    override fun getDailyActivityKey() = getConfigurationValue(DASHBOARD_DAILY_ACTIVITY)
    override fun getStepsAchievementsKey() = getConfigurationValue(DAILY_ACTIVITY_ACHIEVEMENTS)
    override fun getDailyActivityCampaignKey() = getConfigurationValue(DAILY_ACTIVITY_CAMPAIGNS)

    override fun get937ComplaintsKey() = getConfigurationValue(SIDE_MENU_937_COMPLAINTS)
    override fun get937AddComplaintKey()  = getConfigurationValue(SIDE_MENU_937_ADD_COMPLAINT)
    override fun getPartnerPageKey() = getConfigurationValue(PARTNERS_PAGE)

    override fun getRefreshTokenExpirationFeatureKey() = getConfigurationValue(REFRESH_TOKEN_EXPIRATION_FEATURE)
    override fun getRefreshTokenFeatureKey() = getConfigurationString(REFRESH_TOKEN_FEATURE)

}