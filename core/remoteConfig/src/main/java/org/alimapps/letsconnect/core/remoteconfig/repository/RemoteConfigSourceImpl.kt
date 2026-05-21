package org.alimapps.letsconnect.core.remoteconfig.repository
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import org.alimapps.letsconnect.core.common.R
import org.alimapps.letsconnect.core.common.di.coroutines.IoDispatcher
import org.alimapps.letsconnect.core.common.extension.isNotNull
import org.alimapps.letsconnect.core.common.logging.debug
import org.alimapps.letsconnect.core.common.utils.toList
import org.alimapps.letsconnect.core.remoteconfig.IRemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigFeaturesConstants.FEATURE_DEFAULTS_LIST
import org.alimapps.letsconnect.core.remoteconfig.RemoteConfigSource
import org.alimapps.letsconnect.core.remoteconfig.model.FeatureConfigurations
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.get

class RemoteConfigSourceImpl
@Inject
constructor(
    @Named(RemoteConfigSource.DEFAULT_FLAGS_ARRAY) private val defaultFlags: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(),
    @Named(RemoteConfigSource.DEFAULT_FLAGS) private val normalFlags: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(),
    @IoDispatcher private val io: CoroutineDispatcher
): IRemoteConfigSource {
    private var featuresList: List<FeatureConfigurations>? = null
    private val exclusiveFeatures: MutableList<String> = mutableListOf()

    override fun initRemoteConfigDefaultFlags() = callbackFlow {
        defaultFlags.run {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_feature_flags_defaults)
            fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val json = defaultFlags.getString(FEATURE_DEFAULTS_LIST)
                        featuresList = when (json.isEmpty()) {
                            true -> null
                            false -> toList(json)
                        }
                        trySend(true)
                        Log.d("RemoteConfigSource", "Fetched and activated Remote Config feature flag values")
                        Log.e("RemoteConfigSource", "Fetched and activated $featuresList")
                    } else {
                        trySend(false)
                        Log.d("RemoteConfigSource", "Failed to fetch Remote Config feature flag values")
                    }
                }
                .addOnFailureListener {
                    trySend(false)
                }
            awaitClose { close() }
        }
    }

    override fun initRemoteConfig() = callbackFlow {
        normalFlags.run {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
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

    override fun reconfigureFeaturesForUserSegmentation(listOfFeatures: List<String>) {
        if (listOfFeatures.isEmpty()) return
        exclusiveFeatures.clear()
        exclusiveFeatures.addAll(listOfFeatures)
        featuresList = toList<FeatureConfigurations>(defaultFlags.getString(FEATURE_DEFAULTS_LIST)).map { config ->
            if (config.featureKey != null && listOfFeatures.contains(config.featureKey)) {
                config.copy(isEnabled = true)
            } else config
        }
    }

    override fun getString(key: String): String {
        Log.d(TAG, "trying to get KEY: $key")
        return normalFlags.getString(key)
    }

    override fun getBoolean(key: String): Boolean {
        Log.d(TAG, "trying to get KEY: $key")
        return normalFlags.getBoolean(key)
    }

    override fun getStringFromJson(key: String, param: String): String {
        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
        val jsonString = normalFlags.getString(key)
        if (jsonString.isNotEmpty()) {
            val parsed = JsonParser().parse(jsonString).asJsonObject
            if (parsed.has(param)) {
                val value = parsed.get(param).asString
                Log.d(TAG, "got KEY: $key, PARAM:$value")
                return value
            } else {
                return ""
            }
        } else {
            Log.d(TAG, "Remote config json is empty")
        }
        return ""
    }

    override fun getBooleanFromJson(key: String, param: String): Boolean {
        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
        val jsonString = normalFlags.getString(key)
        if (jsonString.isNotEmpty()) {
            val parsed = JsonParser().parse(jsonString).asJsonObject
            if (parsed.has(param)) {
                val value = parsed.get(param).asBoolean
                Log.d(TAG, "got KEY: $key, PARAM:$value")
                return value
            } else {
                return false
            }
        } else {
            Log.d(TAG, "Remote config json is empty")
        }
        return false
    }

    override fun getLongFromJson(key: String, param: String): Long {
        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
        val jsonString = normalFlags.getString(key)
        if (jsonString.isNotEmpty()) {
            val parsed = JsonParser().parse(jsonString).asJsonObject
            if (parsed.has(param)) {
                val value = parsed.get(param).asLong
                Log.d(TAG, "got KEY: $key, PARAM:$value")
                return value
            } else {
                return 0
            }
        } else {
            Log.d(TAG, "Remote config json is empty")
        }
        return 0
    }

    override fun getIntFromJson(key: String, param: String): Int {
        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
        val jsonString = normalFlags.getString(key)
        if (jsonString.isNotEmpty()) {
            val parsed = JsonParser().parse(jsonString).asJsonObject
            return if (parsed.has(param)) {
                val value = parsed.get(param).asInt
                Log.d(TAG, "got KEY: $key, PARAM:$value")
                value
            } else {
                0
            }
        } else {
            Log.d(TAG, "Remote config json is empty")
        }
        return 0
    }

    override fun <T> getObjectListFromJson(key: String, param: String): List<T> {
        return runCatching {
            val gson = Gson()
            val typeToken = object : TypeToken<T>() {}.type
            Log.d("RemoteConfigSource", "trying to get KEY: $key, PARAM:$param")
            val jsonString = getString(key)
            if (jsonString.isNotEmpty()) {
                val parsed = JsonParser().parse(jsonString).asJsonObject
                if (parsed.has(param)) {
                    val value = parsed.get(param).asJsonArray
                    Log.d("RemoteConfigSource", "got KEY: $key, PARAM:$value")
                    val list = ArrayList<T>()
                    for (i in 0 until value.size()) {
                        Log.d("RemoteConfigSource", "item: " + i + " - " + value[i].toString())
                        val itemJsonString = value[i].toString()
                        list.add(gson.fromJson(itemJsonString, typeToken))
                    }
                    return list
                } else {
                    return emptyList()
                }
            } else {
                Log.e("RemoteConfigSource", "Remote config json is empty")
            }
            return emptyList()
        }.getOrDefault(emptyList())
    }

    override fun <T> getObjectFromJson(key: String, clazz: Class<T>?): T? {
        val json = getString(key)
        Log.e("initDebugRemoteConfigKeys", "getJsonString for key: $key with result: $json")
        return when (json.isEmpty()) {
            true -> null
            false -> toModel(json, clazz)
        }
    }

    @Deprecated("Please use [getObjectFromJson] with clazz parameter to make it more convenient")
    override fun <T> getObjectFromJson(key: String): T? {
        val json = getString(key)
        Log.e("initDebugRemoteConfigKeys", "getJsonString for key: $key with result: $json")
        return when (json.isEmpty()) {
            true -> null
            false -> toModel(json)
        }
    }

    override fun getFeatureFromJson(featureKey: String): FeatureConfigurations? {
        return try {
            if (featuresList.isNullOrEmpty()) {
                val json = defaultFlags.getString(FEATURE_DEFAULTS_LIST)
                featuresList = when (json.isEmpty()) {
                    true -> null
                    false -> toList(json)
                }
            }
            val featureValue = featuresList?.find { it.featureKey == featureKey }
            debug("-----------------------------------------------------------------")
            debug("config of feature: $featureKey result: ${featureValue?.isEnabled}")
            debug("-----------------------------------------------------------------")
            return if (featureValue.isNotNull() && exclusiveFeatures.contains(featureValue?.featureKey)) {
                featureValue?.isEnabled = true
                featureValue
            } else featureValue
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val TAG = "RemoteConfigSourceImpl"
    }

    private fun <T> toModel(json: String, clazz: Class<T>?): T? {
        val gson = Gson()
        return gson.fromJson(json, clazz)
    }

    private fun <T> toModel(json: String): T? {
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }
}