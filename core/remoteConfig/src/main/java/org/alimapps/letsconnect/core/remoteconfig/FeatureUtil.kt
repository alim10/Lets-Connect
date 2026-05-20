package org.alimapps.letsconnect.core.remoteconfig

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureUtil @Inject constructor(
    private val remoteConfigRepository: RemoteConfigSource
) {
    fun isFeatureEnabledFromBoolean(featureName: String = PARAM_FEATURE_SPL_VISIBILITY): Boolean =
        remoteConfigRepository.getBoolean(featureName)

    companion object {
        const val PARAM_FEATURE_SPL_VISIBILITY = "spl_visibility"
    }
}