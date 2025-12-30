package org.alimapps.letsconnect.core.common.remoteconfig.data.repository
import android.util.Log
import org.alimapps.letsconnect.core.R
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_ACHIEVEMENTS
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.DailyActivity.DAILY_ACTIVITY_CAMPAIGNS
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.Dashboard.DASHBOARD_DAILY_ACTIVITY
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.FEATURE_DEFAULTS_LIST
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.PartnersPage.PARTNERS_PAGE
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_ADD_COMPLAINT
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.SideMenu.SIDE_MENU_937_COMPLAINTS
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.utils.Const.isProd
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfigSource: RemoteConfigSource,
    @Named(RemoteConfigSource.DEFAULT_FLAGS_ARRAY) private val defaultFlags: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(),
    @Named(RemoteConfigSource.DEFAULT_FLAGS) private val normalFlags: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(),
) : IRemoteConfigRepository {
    private fun getConfigurationValue(key: String): Boolean {
        return remoteConfigSource.getFeatureFromJson(
            key
        )?.isEnabled ?: true
    }
    private fun getConfigurationData(key: String): Any? {
        return remoteConfigSource.getFeatureFromJson(
            key
        )?.data
    }
    
    private fun getConfigurationString(key: String) = remoteConfigSource.getString(key)
    
    
    override fun initRemoteConfigDefaultFlags() = callbackFlow {
        defaultFlags.run {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (!isProd) 0 else 1800
            }
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_feature_flags_defaults)
            fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(true)
                        // Do something
                        Log.d("RemoteConfigSource", "Fetched and activated Remote Config feature flag values")
                    } else {
                        trySend(false)
                        // Handle error
                        Log.d("RemoteConfigSource", "Failed to fetch Remote Config feature flag values")
                    }
                }
                .addOnFailureListener {
                    trySend(false)
                }
            awaitClose()
        }
    }
    
    override fun initRemoteConfig() = callbackFlow {
        normalFlags.run {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (!isProd) 0 else 1800
            }
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(true)
                        // Do something
                        Log.d("RemoteConfigSource", "Fetched and activated Remote Config feature flag values")
                    } else {
                        trySend(false)
                        // Handle error
                        Log.d("RemoteConfigSource", "Failed to fetch Remote Config feature flag values")
                    }
                }
                .addOnFailureListener {
                    trySend(false)
                }
            awaitClose()
        }
    }
    
    override fun getFeatureDefaultsListKey() = getConfigurationValue(FEATURE_DEFAULTS_LIST)
    override fun getDailyActivityKey() = getConfigurationValue(DASHBOARD_DAILY_ACTIVITY)
    override fun getStepsAchievementsKey() = getConfigurationValue(DAILY_ACTIVITY_ACHIEVEMENTS)
    override fun getDailyActivityCampaignKey() = getConfigurationValue(DAILY_ACTIVITY_CAMPAIGNS)
    override fun get937ComplaintsKey() = getConfigurationValue(SIDE_MENU_937_COMPLAINTS)
    override fun get937AddComplaintKey()  = getConfigurationValue(SIDE_MENU_937_ADD_COMPLAINT)
    override fun getPartnerPageKey() = getConfigurationValue(PARTNERS_PAGE)
}