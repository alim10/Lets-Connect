package org.alimapps.letsconnect.core.remoteconfig

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.remoteconfig.model.FeatureConfigurations

interface IRemoteConfigSource {
    fun initRemoteConfigDefaultFlags(): Flow<Boolean>
    fun initRemoteConfig(): Flow<Boolean>
    fun reconfigureFeaturesForUserSegmentation(listOfFeatures: List<String>)

    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getStringFromJson(key: String, param: String): String
    fun getBooleanFromJson(key: String, param: String): Boolean
    fun getLongFromJson(key: String, param: String): Long
    fun getIntFromJson(key: String, param: String): Int
    fun <T> getObjectListFromJson(key: String, param: String): List<T>
    fun <T> getObjectFromJson(key: String): T?
    fun <T> getObjectFromJson(key: String, clazz: Class<T>? = null): T?
    fun getFeatureFromJson(featureKey: String): FeatureConfigurations?
}