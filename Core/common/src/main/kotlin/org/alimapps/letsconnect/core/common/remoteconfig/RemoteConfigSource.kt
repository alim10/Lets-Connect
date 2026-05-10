//package org.alimapps.letsconnect.core.common.remoteconfig
//import android.util.Log
//import org.alimapps.letsconnect.core.common.logging.debug
//import com.google.gson.Gson
//import com.google.gson.JsonParser
//import com.google.gson.reflect.TypeToken
//import org.alimapps.letsconnect.core.common.remoteconfig.RemoteConfigFeaturesConstants.FEATURE_DEFAULTS_LIST
//import org.alimapps.letsconnect.core.common.remoteconfig.data.model.FeatureConfigurations
//import org.alimapps.letsconnect.core.common.utils.toList
//import org.alimapps.letsconnect.core.common.utils.toModel
//import com.google.firebase.remoteconfig.FirebaseRemoteConfig
//import javax.inject.Inject
//import javax.inject.Named
//import javax.inject.Singleton
//
//@Singleton
//class RemoteConfigSource @Inject constructor(
//) {
//    @Inject
//    @Named(DEFAULT_FLAGS_ARRAY)
//    lateinit var defaultFlags: FirebaseRemoteConfig
//
//    @Inject
//    @Named(DEFAULT_FLAGS)
//    lateinit var normalFlags: FirebaseRemoteConfig
//
//    fun getString(key: String): String {
//        Log.d(TAG, "trying to get KEY: $key")
//        return normalFlags.getString(key)
//    }
//
//    fun getBoolean(key: String): Boolean {
//        Log.d(TAG, "trying to get KEY: $key")
//        return normalFlags.getBoolean(key)
//    }
//
//    fun getStringFromJson(key: String, param: String): String {
//        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
//        val jsonString = normalFlags.getString(key)
//        if (jsonString.isNotEmpty()) {
//            val parsed = JsonParser().parse(jsonString).asJsonObject
//            if (parsed.has(param)) {
//                val value = parsed.get(param).asString
//                Log.d(TAG, "got KEY: $key, PARAM:$value")
//                return value
//            } else {
//                return ""
//            }
//        } else {
//            Log.d(TAG, "Remote config json is empty")
//        }
//        return ""
//    }
//
//    fun getBooleanFromJson(key: String, param: String): Boolean {
//        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
//        val jsonString = normalFlags.getString(key)
//        if (jsonString.isNotEmpty()) {
//            val parsed = JsonParser().parse(jsonString).asJsonObject
//            if (parsed.has(param)) {
//                val value = parsed.get(param).asBoolean
//                Log.d(TAG, "got KEY: $key, PARAM:$value")
//                return value
//            } else {
//                return false
//            }
//        } else {
//            Log.d(TAG, "Remote config json is empty")
//        }
//        return false
//    }
//
//    fun getLongFromJson(key: String, param: String): Long {
//        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
//        val jsonString = normalFlags.getString(key)
//        if (jsonString.isNotEmpty()) {
//            val parsed = JsonParser().parse(jsonString).asJsonObject
//            if (parsed.has(param)) {
//                val value = parsed.get(param).asLong
//                Log.d(TAG, "got KEY: $key, PARAM:$value")
//                return value
//            } else {
//                return 0
//            }
//        } else {
//            Log.d(TAG, "Remote config json is empty")
//        }
//        return 0
//    }
//
//    fun getIntFromJson(key: String, param: String): Int {
//        Log.d(TAG, "trying to get KEY: $key, PARAM:$param")
//        val jsonString = normalFlags.getString(key)
//        if (jsonString.isNotEmpty()) {
//            val parsed = JsonParser().parse(jsonString).asJsonObject
//            return if (parsed.has(param)) {
//                val value = parsed.get(param).asInt
//                Log.d(TAG, "got KEY: $key, PARAM:$value")
//                value
//            } else {
//                0
//            }
//        } else {
//            Log.d(TAG, "Remote config json is empty")
//        }
//        return 0
//    }
//
//    inline fun <reified T> getObjectListFromJson(key: String, param: String): List<T> {
//        return runCatching {
//            val gson = Gson()
//            val typeToken = object : TypeToken<T>() {}.type
//            Log.d("RemoteConfigSource", "trying to get KEY: $key, PARAM:$param")
//            val jsonString = getString(key)
//            if (jsonString.isNotEmpty()) {
//                val parsed = JsonParser().parse(jsonString).asJsonObject
//                if (parsed.has(param)) {
//                    val value = parsed.get(param).asJsonArray
//                    Log.d("RemoteConfigSource", "got KEY: $key, PARAM:$value")
//                    val list = ArrayList<T>()
//                    for (i in 0 until value.size()) {
//                        Log.d("RemoteConfigSource", "item: " + i + " - " + value[i].toString())
//                        val itemJsonString = value[i].toString()
//                        list.add(gson.fromJson(itemJsonString, typeToken))
//                    }
//                    return list
//                } else {
//                    return emptyList()
//                }
//            } else {
//                Log.d("RemoteConfigSource", "Remote config json is empty")
//            }
//            return emptyList()
//        }.getOrDefault(emptyList())
//    }
//
//    inline fun <reified T> getObjectFromJson(key: String): T? {
//        val json = getString(key)
//        debug("getJsonString for key: $key with result: $json")
//        return when (json.isEmpty()) {
//            true -> null
//            false -> toModel(json)
//        }
//    }
//
//    var featuresList: List<FeatureConfigurations>? = null
//    fun getFeatureFromJson(featureKey: String): FeatureConfigurations? {
//        return try {
//            if (featuresList.isNullOrEmpty()) {
//                val json = defaultFlags.getString(FEATURE_DEFAULTS_LIST)
////                debug("getJsonString for key: $FEATURE_DEFAULTS_LIST with result: $json")
//
//                featuresList = when (json.isEmpty()) {
//                    true -> null
//                    false -> toList(json)
//                }
//            }
//            val featureValue = featuresList?.find { it.featureKey == featureKey }
//            debug("-----------------------------------------------------------------")
//            debug("config of feature: $featureKey result: ${featureValue?.isEnabled}")
//            debug("-----------------------------------------------------------------")
//            featureValue
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    companion object {
//        private const val TAG = "RemoteConfigSource"
//        const val KEY_FORCE_UPDATE = "android_store_update"
//        const val DEFAULT_FLAGS_ARRAY = "default_flag_array"
//        const val DEFAULT_FLAGS = "default_flag"
//        const val PARAM_CURRENT_STORE_VERSION = "current_store_version_code"
//        const val PARAM_IS_FORCE_UPDATE = "is_force_update_allowed"
//
//        const val PARAM_DEPENDENTS = "dependents"
//        const val PARAM_PREGNANCY = "pregnancy"
//        const val PARAM_LAB_TESTS = "lab_tests"
//
//        const val KEY_CACHE_INTERVALS = "android_cache_intervals"
//        const val PARAM_REFRESH_TOKEN = "refresh_token"
//        const val PARAM_USER_PROFILE = "user_profile"
//        const val PARAM_TETAMMAN_DASHBOARD = "tetamman_dashboard"
//        const val PARAM_APPOINTMENTS_MAWID = "appointments_mawid"
//        const val PARAM_APPOINTMENTS_TELEHEALTH = "appointments_telehealth"
//        const val PARAM_TELEHEALTH_CONFIG = "telehealth_config"
//        const val PARAM_VITAL_SIGNS_STEPS_GET = "vital_signs_steps_get"
//        const val PARAM_VITAL_SIGNS_STEPS_POST = "vital_signs_steps_post"
//        const val PARAM_TEAM_CARE = "team_care"
//        const val PARAM_VIRUS_GET_STATUS = "virus_get_status"
//        const val PARAM_VIRUS_SYMPTOMS_PERIOD = "virus_symptoms_period"
//        const val KEY_VIRUS_FIRST_DOSE = "first_dose"
//        const val KEY_VIRUS_SECOND_DOSE = "second_dose"
//        const val KEY_VIRUS_THIRD_DOSE = "third_dose"
//        const val KEY_VIRUS_FOURTH_DOSE = "fourth_dose"
//
//        const val KEY_HEALTH_PROFILE_LOOKUP = "android_health_profile_lookup"
//        const val PARAM_ALLERGIES = "allergies"
//        const val PARAM_DISEASES = "diseases"
//
//        const val KEY_VITAL_SIGNS_LINKS = "vital_signs_dashboard_links"
//        const val KEY_VIRUS_DOSE_RESCHEDULE_OPTIONS = "virus_vaccine_reschedule_options"
//
//        const val KEY_NAPHIES_CARD_VISIBILITY = "nphies_card_visibility"
//        const val KEY_SHOW_NAPHIES_CONSENT = "show_nphies_consent"
//
//        const val KEY_NVR_DATA = "nvr_vaccines_data"
//        const val PARAM_VIRUS_VACCINES_GROUP_ID = "virus_vaccines_group_id"
//        const val PARAM_CHILD_VACCINES_GROUP_ID = "child_vaccines_group_id"
//        const val PARAM_ADULT_VACCINES_GROUP_ID = "adult_vaccines_group_id"
//        const val PARAM_HAJJ_VACCINES_GROUP_ID = "hajj_vaccines_group_id"
//        const val PARAM_ADULT_VACCINES_CLINIC_ID = "adult_vaccines_clinic_id"
//        const val PARAM_ADULT_VACCINES_CLINIC_SERVICE_CODE = "adult_vaccines_clinic_service_code"
//        const val PARAM_ADULT_VACCINES_BOOKING_ENABLED = "is_book_adult_vaccines_available"
//
//        const val KEY_TBC_WEB_SOCKET_URL = "tbc_signal_chat_url"
//        const val PARAM_TBC_SOCKET_URL = "tbc_socket_url"
//    }
//}