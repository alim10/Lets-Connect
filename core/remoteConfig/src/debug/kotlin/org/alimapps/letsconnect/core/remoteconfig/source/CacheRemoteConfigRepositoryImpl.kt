package org.alimapps.letsconnect.core.remoteconfig.source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.alimapps.letsconnect.core.remoteconfig.db.DeveloperOptionsDatabase
import org.alimapps.letsconnect.core.remoteconfig.model.CacheFeatureFlagModel
import org.alimapps.letsconnect.core.common.di.coroutines.IoDispatcher
import org.alimapps.letsconnect.core.common.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_ACHIEVEMENTS
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_CAMPAIGNS
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.Dashboard.DASHBOARD_DAILY_ACTIVITY
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.FEATURE_DEFAULTS_LIST
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.PartnersPage.PARTNERS_PAGE
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_ADD_COMPLAINT
import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_COMPLAINTS
import org.alimapps.letsconnect.core.common.remoteconfig.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
import javax.inject.Inject

class CacheRemoteConfigRepositoryImpl
@Inject 
constructor(
    private val remoteConfigSource: IRemoteConfigSource,
    db: DeveloperOptionsDatabase,
    @IoDispatcher private val io: CoroutineDispatcher
): IRemoteConfigRepository {
    private val dao = db.featureFlagsDao()

    override fun initRemoteConfigDefaultFlags() = remoteConfigSource.initRemoteConfigDefaultFlags()

    override fun initRemoteConfig() = remoteConfigSource.initRemoteConfig()
//
//    override fun reconfigureFeaturesForUserSegmentation(listOfFeatures: List<String>) {
//        remoteConfigSource.reconfigureFeaturesForUserSegmentation(listOfFeatures)
//        for (key in listOfFeatures) getConfigurationValue(key)
//    }

    private fun getConfigurationValue(key: String, functionName: String? = null, defValue: Boolean = true) = runCatching {
        GlobalScope.launch(io) {
            dao.insertFeatureFlag(
                CacheFeatureFlagModel(
                    flagId = key,
                    name = key.replace("_", " ") + " " + key,
                    functionName = functionName ?: (key.replace("_", " ") + " " + key),
                    isEnable = remoteConfigSource.getFeatureFromJson(key)?.isEnabled ?: defValue,
                    data = null
                )
            )
        }
        true
    }.getOrNull() ?: defValue

    private fun getConfigurationData(key: String, default: Any? = null, functionName: String? = null) = runCatching {
        GlobalScope.launch(io) {
            dao.insertFeatureFlag(
                CacheFeatureFlagModel(
                    flagId = key,
                    name = key.replace("_", " ") + " " + key,
                    functionName = functionName ?: (key.replace("_", " ") + " " + key),
                    isEnable = null,
                    data = (remoteConfigSource.getFeatureFromJson(key)?.data ?: remoteConfigSource.getString(key)).takeIf { it.toString().trim().isNotEmpty() } ?: default
                )
            )
        }
        default
    }.getOrNull() ?: default

    private fun getConfigurationString(key: String, default: String? = null, functionName: String? = null) = runCatching {
        GlobalScope.launch(io) {
            dao.insertFeatureFlag(
                CacheFeatureFlagModel(
                    flagId = key,
                    name = key.replace("_", " ") + " " + key,
                    functionName = functionName ?: (key.replace("_", " ") + " " + key),
                    isEnable = null,
                    data = remoteConfigSource.getString(key) ?: default
                )
            )
        }
        default
    }.getOrNull() ?: default

    override fun getFeatureDefaultsListKey() = getConfigurationValue(FEATURE_DEFAULTS_LIST, functionName = "getFeatureDefaultsListKey")
    override fun getDailyActivityKey() = getConfigurationValue(DASHBOARD_DAILY_ACTIVITY, functionName = "getDailyActivityKey")
    override fun getStepsAchievementsKey() = getConfigurationValue(DAILY_ACTIVITY_ACHIEVEMENTS, functionName = "getStepsAchievementsKey")
    override fun getDailyActivityCampaignKey() = getConfigurationValue(DAILY_ACTIVITY_CAMPAIGNS, functionName = "getDailyActivityCampaignKey")



    override fun get937ComplaintsKey() = getConfigurationValue(SIDE_MENU_937_COMPLAINTS, functionName = "get937ComplaintsKey")
    override fun get937AddComplaintKey()  = getConfigurationValue(SIDE_MENU_937_ADD_COMPLAINT, functionName = "get937AddComplaintKey")
    override fun getPartnerPageKey() = getConfigurationValue(PARTNERS_PAGE, functionName = "getPartnerPageKey")

}