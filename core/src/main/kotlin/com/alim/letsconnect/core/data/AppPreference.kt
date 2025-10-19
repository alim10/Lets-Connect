package com.alim.letsconnect.core.data

import android.content.Context
import com.alim.letsconnect.core.utils.Const
import com.google.gson.Gson

import org.intellij.lang.annotations.Language
import java.util.*

class AppPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private companion object {
        const val PREF_NAME = "karwaty_preference"

        const val DEVICE_TOKEN = "device_token"
        const val LANGUAGE = "language"
        const val GOOGLE_KEY = "google_key"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val LOCATIONS = "location"
        const val USER_ID = "user_id"
        const val USER_TYPE = "user_type"
        const val STATUS_ID = "status_id"
        const val USER_GUID = "user_guid"
        const val CUSTOMER_ID = "customer_id"
        const val ACCESS_TOKEN = "session_token"
        const val FIRST_NAME = "first_name"
        const val MIDDLE_NAME = "middle_name"
        const val LAST_NAME = "last_name"
        const val FULL_NAME = "full_name"
        const val ADDRESS = "address"
        const val ZIP_CODE = "zip_code"
        const val PROFILE_PIC = "profile_pic"
        const val PHONE_NUMBER = "phone_number"
        const val COUNTRY_CODE = "country_code"
        const val COUNTRY_LIST = "country_list"
        const val PHONE_COUNTRY_CODE = "phone_country_code"
        const val EMAIL = "email"
        const val IS_PUSH_SOUND_ON = "is_push_sound_on"
        const val REFERRAL = "referral"
        const val IS_LOCATION_UPDATE = "is_location_update"
        const val IS_PHONE_NUMBER_VERIFIED = "is_phone_number_verified"
        const val LANGUAGE_CODE = "language"
        const val LANGUAGE_NAME = "language_name"
        const val ANDROID_ID = "android_id"
        const val COMPANY_DATA = "companyData"
        const val COUNTRY = "country"
        const val CURRENCY = "currency"
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> preferences.getString(key, "")
            Boolean::class.java -> preferences.getBoolean(key, false)
            Float::class.java -> preferences.getFloat(key, -1f)
            Double::class.java -> preferences.getFloat(key, -1f)
            Int::class.java -> preferences.getInt(key, -1)
            Long::class.java -> preferences.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = preferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun setLanguages(language: ArrayList<Language>) {
        val edit = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(language)
        edit.putString(LANGUAGE, json)
        edit.apply()
    }
//    fun setCurrentLocation(language: LatLng) {
//        val edit = preferences.edit()
//        val gson = Gson()
//        val json = gson.toJson(language)
//        edit.putString(LANGUAGE, json)
//        edit.apply()
//    }

    fun setLanguageCode(code: String?) {
        val edit = preferences.edit()
        edit.putString(LANGUAGE_CODE, code)
        edit.apply()
    }

    val languageCode: String get() = preferences.getString(LANGUAGE_CODE, "").toString()

    fun setLanguageName(name: String?) {
        val edit = preferences.edit()
        edit.putString(LANGUAGE_NAME, name)
        edit.apply()
    }

    fun putDeviceToken(deviceToken: String?) {
        val editor = preferences.edit()
        editor.putString(DEVICE_TOKEN, deviceToken)
        editor.apply()
    }

    val deviceToken: String get() = preferences.getString(DEVICE_TOKEN, null).toString()

    fun putAccessToken(sessionToken: String?) {
        val edit = preferences.edit()
        edit.putString(ACCESS_TOKEN, sessionToken)
        edit.apply()
    }

    val accessToken: String get() = preferences.getString(ACCESS_TOKEN, "").toString()

    val languageName: String
        get() = preferences.getString(
            LANGUAGE_NAME,
            Locale.getDefault().displayLanguage
        ).toString()

    fun putUserFullName(lastName: String?) {
        val edit = preferences.edit()
        edit.putString(FULL_NAME, lastName)
        edit.apply()
    }

    val userFullName: String get() = preferences.getString(FULL_NAME, "").toString()

    fun setBusinessVisited(isVisit: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(LONGITUDE, isVisit)
        editor.apply()
    }
    /*-----------------*/
    fun putIsLocationUpdate(isVerified: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_LOCATION_UPDATE, isVerified)
        editor.apply()
    }
    val isLocationUpdate: Boolean
        get() = preferences.getBoolean(
            IS_LOCATION_UPDATE,
            false
        )
    /*-------------------------*/
    fun putPhoneNumberVerified(isVerified: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_PHONE_NUMBER_VERIFIED, isVerified)
        editor.apply()
    }
    val isPhoneNumberVerified: Boolean
        get() = preferences.getBoolean(
            IS_PHONE_NUMBER_VERIFIED,
            false
        )

    fun putUserId(userId: String?) {
        val edit = preferences.edit()
        edit.putString(USER_ID, userId)
        edit.apply()
    }

    val userId: String get() = preferences.getString(USER_ID, "").toString()

    fun fetchUserId(): String {
        return get(USER_ID, String::class.java)
    }


    fun putUserType(userType: Int) {
        val edit = preferences.edit()
        edit.putInt(USER_TYPE, userType)
        edit.apply()
    }

    val userType: Int get() = preferences.getInt(USER_TYPE, 0)

    fun putEmail(userEmail: String?) {
        val edit = preferences.edit()
        edit.putString(EMAIL, userEmail)
        edit.apply()
    }

    fun putEmail(): String? {
        return preferences.getString(EMAIL, "")
    }

    fun putReferral(referral: String?) {
        val edit = preferences.edit()
        edit.putString(REFERRAL, referral)
        edit.apply()
    }

    fun putProfilePic(profilePic: String?) {
        val edit = preferences.edit()
        edit.putString(PROFILE_PIC, profilePic)
        edit.apply()
    }

    val profilePic: String get() = preferences.getString(PROFILE_PIC, null).toString()

    fun putLatitude(latitude: Double?) {
        val editor = preferences.edit()
        editor.putLong(LATITUDE, (latitude ?: 0.0).toLong())
        editor.apply()
    }

    val latitude: Long
        get() = preferences.getLong(LATITUDE, 0)

    fun putLongitude(latitude: Double?) {
        val editor = preferences.edit()
        editor.putLong(LONGITUDE, (latitude ?: 0.0).toLong())
        editor.apply()
    }

    val longitude: Long
        get() = preferences.getLong(LONGITUDE, 0)

    fun putGoogleKey(key: String) {
        val editor = preferences.edit()
        editor.putString(GOOGLE_KEY, key.trim { it <= ' ' })
        editor.apply()
    }

    val googleKey: String
        get() = preferences.getString(
            GOOGLE_KEY,
            null
        ).toString()


    fun setCountyCode(countryCode: String?) {
        val edit = preferences.edit()
        edit.putString(COUNTRY_CODE, countryCode)
        edit.apply()
    }

    val countyCode: String
        get() = preferences.getString(COUNTRY_CODE, "").toString()

    val phoneCountyCode: String
        get() = preferences.getString(
            PHONE_COUNTRY_CODE,
            ""
        ).toString()

    fun putPhoneNumber(phoneNumber: String?) {
        val edit = preferences.edit()
        edit.putString(PHONE_NUMBER, phoneNumber)
        edit.apply()
    }

    val phoneNumber: String
        get() = preferences.getString(
            PHONE_NUMBER,
            ""
        ).toString()

    fun putAndroidId(id: String?) {
        val edit = preferences.edit()
        edit.putString(ANDROID_ID, id)
        edit.apply()
    }

    val androidId: String
        get() = preferences.getString(ANDROID_ID, "").toString()


    val appCurrency: String get() = preferences.getString(CURRENCY, Const.SELECTED_CURRENCY).toString()

    fun putAppCurrency(sessionToken: String?) {
        val edit = preferences.edit()
        edit.putString(CURRENCY, sessionToken)
        edit.apply()
    }
    fun clearPrefData() {
        putUserFullName("")
        putUserId("")
        putAccessToken("")
        putAppCurrency("")
//        putLatitude(0.0)
//        putLongitude(0.0)
    }

}