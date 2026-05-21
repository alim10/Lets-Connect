package org.alimapps.letsconnect.core.session

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences
) : SharedPrefsRepository {

    // This preference is not cleared/deleted when the user logs out
    private var appConfigPreference: SharedPreferences =
        context.applicationContext.getSharedPreferences(
            APP_PREF_CONFIG, Context.MODE_PRIVATE
        )

    override var nationalID: String?
        get() = getValue(PREF_NATIONAL_ID)
        set(value) = sharedPreferences.edit().putString(PREF_NATIONAL_ID, value).apply()

    override var accessToken: String?
        get() = getValue(PREF_ACCESS_TOKEN)
        set(value) {
            sharedPreferences.edit().putString(PREF_ACCESS_TOKEN, value).commit()
            isTokenExpired(value)
        }

    override var tokenExpiredDate: Long?
        get() = getValue(PREF_EXPIRED_ACCESS_TOKEN)
        set(value) {
            sharedPreferences.edit().putLong(PREF_EXPIRED_ACCESS_TOKEN, value ?: 0L).commit()
        }

    private fun isTokenExpired(token: String?) {
        Log.e("isTokenExpired", "Token VALUE >> $token")
        if (!token.isNullOrEmpty()) {
            val expTime = try { decodeJWT(token).getLong("exp") } catch (e: Exception) { Date().time }

            val calendar = Calendar.getInstance().apply {
                time = Date(expTime * 1000)
                add(Calendar.SECOND, -5)
            }
            val adjustedExpirationDate = calendar.time
            Log.e("isTokenExpired", "Expired Date >> ${calendar.time}")
            Log.e("isTokenExpired", "Now Date     >> ${Date()}")
            tokenExpiredDate = adjustedExpirationDate.time
        } else tokenExpiredDate = Date().time
    }

    private fun decodeJWT(token: String): JSONObject {
        val parts = token.split(".")
        val payload = parts[1]
        val decodedPayload = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            String(java.util.Base64.getUrlDecoder().decode(payload))
        else String(decodeBase64Url(payload))
        return JSONObject(decodedPayload)
    }

    private fun decodeBase64Url(base64Url: String): ByteArray {
        val modifiedBase64 = base64Url
            .replace("-", "+")
            .replace("_", "/")

        val padding = modifiedBase64.length % 4
        val paddedBase64 = if (padding > 0)
            modifiedBase64 + "=".repeat(4 - padding)
        else modifiedBase64

        return Base64.decode(paddedBase64, Base64.DEFAULT)
    }

    override var refreshToken: String?
        get() = getValue(PREF_REFRESH_TOKEN)
        set(value) {
            sharedPreferences.edit().putString(PREF_REFRESH_TOKEN, value).commit()
        }

    override var isFcmTokenRegistered: Boolean
        get() = getValue(PREF_IS_FCM_TOKEN_SENT) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_IS_FCM_TOKEN_SENT, value).apply()
        }
    override var allowProfileUpdate: Boolean?
        get() = getValue(PREF_ALLOW_PROFILE_UPDATE) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_ALLOW_PROFILE_UPDATE, value ?: false).apply()
        }
    override var profileUpdatedAt: String?
        get() = appConfigPreference.getString(PREF_PROFILE_UPDATED_AT, null) ?: defaultLang()
        set(value) {
            appConfigPreference.edit().putString(PREF_PROFILE_UPDATED_AT, value).apply()
        }

    override var isStepPermissionGranted: Boolean
        get() = getValue(PREF_STEPS_PERMISSION) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_STEPS_PERMISSION, value).apply()
        }

    private fun defaultLang(): String {
        val currentLang = Locale.getDefault().language
        val isLangSupported = currentLang in supportedLang
        return if (isLangSupported) currentLang else ENGLISH
    }

    override var locale: String
        get() = appConfigPreference.getString(PREF_LOCALE, null) ?: defaultLang()
        set(value) {
            appConfigPreference.edit().putString(PREF_LOCALE, value).apply()
        }

    override var userName: String?
        get() = getValue(PREF_USER_NAME) ?: ""
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_NAME, value).apply()
        }
    override var firstNameAr: String?
        get() = getValue(UserKeys.FIRST_NAME_AR)
        set(value) {
            set(UserKeys.FIRST_NAME_AR, value)
        }
    override var firstNameEn: String?
        get() = getValue(UserKeys.FIRST_NAME_EN)
        set(value) {
            set(UserKeys.FIRST_NAME_EN, value)
        }
    override var secondNameAr: String?
        get() = getValue(UserKeys.SECOND_NAME_AR)
        set(value) {
            set(UserKeys.SECOND_NAME_AR, value)
        }
    override var secondNameEn: String?
        get() = getValue(UserKeys.SECOND_NAME_EN)
        set(value) {
            set(UserKeys.SECOND_NAME_EN, value)
        }
    override var lastNameAr: String?
        get() = getValue(UserKeys.LAST_NAME_AR)
        set(value) {
            set(UserKeys.LAST_NAME_AR, value)
        }
    override var lastNameEn: String?
        get() = getValue(UserKeys.LAST_NAME_EN)
        set(value) {
            set(UserKeys.LAST_NAME_EN, value)
        }

    override var userFullName: String?
        get() = getValue(PREF_USER_FULL_NAME) ?: ""
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_FULL_NAME, value).apply()
        }

    override var
            isLoggedIn: Boolean
        get() = getValue(PREF_LOGGED_IN) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_LOGGED_IN, value).apply()
        }

    override var tempIdentifier: String?
        get() = getValue(PREF_TEMP_IDENTIFIER)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_IDENTIFIER, value).apply()
        }

    override var tempIdentifierForTwoOtp: String?
        get() = getValue(PREF_TEMP_IDENTIFIER2)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_IDENTIFIER2, value).apply()
        }

    override var tempNationalId: String?
        get() = getValue(PREF_TEMP_NATIONAL_ID)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_NATIONAL_ID, value).apply()
        }

    override var tempPassword: String?
        get() = getValue(PREF_TEMP_PASSWORD)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_PASSWORD, value).apply()
        }

    override var tempUserKey: String?
        get() = getValue(PREF_TEMP_USER_KEY)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_USER_KEY, value).apply()
        }

    override var showHealthSummaryToolTip: Boolean
        get() = getValue(PREF_SHOW_HEALTH_SUMMARY_TIP_TOOL) ?: true
        set(value) {
            set(PREF_SHOW_HEALTH_SUMMARY_TIP_TOOL, value)
        }

    override var shouldShowEatizazPrivacy: Boolean
        get() = getValue(PREF_SHOW_EATIZAZ_PRIVACY) ?: true
        set(value) {
            set(PREF_SHOW_EATIZAZ_PRIVACY, value)
        }

    // determine if the user click skip in the "JoinEmshFragment" to not display it again
    override var isUserSkippedEmshIntro: Boolean
        get() = getValue(PREF_IS_USER_SKIP_EMSH_INTRO) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_IS_USER_SKIP_EMSH_INTRO, value).apply()
        }

    override var forceUpdate: Boolean
        get() = getValue(PREF_FORCE_UPDATE) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_FORCE_UPDATE, value).apply()
        }

    override var normalUpdate: Boolean
        get() = getValue(PREF_NORMAL_UPDATE) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_NORMAL_UPDATE, value).apply()
        }

    override var bmiTimestamp: String? //TODO temporary until API returns bmi data
        get() = getValue(PREF_BMI_TIMESTAMP) ?: ""
        set(value) {
            sharedPreferences.edit().putString(PREF_BMI_TIMESTAMP, value).apply()
        }

    override var needToSubmitDailySurvey: Boolean
        get() = getValue(PREF_SUBMIT_DAILY_SURVEY) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_SUBMIT_DAILY_SURVEY, value).apply()
        }
    override var languageCode: String
        get() = getValue("LANGUAGE_CODE") ?: "EN"
        set(value) {
            sharedPreferences.edit().putString("LANGUAGE_CODE", value).apply()
        }

    override var languageName: String
        get() = getValue("LANGUAGE_NAME") ?: "English"
        set(value) {
            sharedPreferences.edit().putString("LANGUAGE_NAME", value).apply()
        }

    override var isActiveQuarantine: Boolean
        get() = getValue(PREF_IS_ACTIVE_QUARANTINE) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_SUBMIT_DAILY_SURVEY, value).apply()
        }

    override var showInAppHttpInterceptor: Boolean
        get() = getValue(PREF_SHOW_IN_APP_HTTP_INTERCEPTOR) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_SHOW_IN_APP_HTTP_INTERCEPTOR, value).commit()
        }

    override var virusStatusResponse: String
        get() = getValue(PREF_VIRUS_STATUS) ?: ""
        set(value) {
            sharedPreferences.edit { putString(PREF_VIRUS_STATUS, value) }
        }

    override var virusVaccineResponse: String
        get() = getValue(PREF_VIRUS_VACCINE) ?: ""
        set(value) {
            sharedPreferences.edit { putString(PREF_VIRUS_VACCINE, value) }
        }

    override var virusProfile: String
        get() = getValue(PREF_VIRUS_TOKEN) ?: ""
        set(value) {
            sharedPreferences.edit { putString(PREF_VIRUS_TOKEN, value) }
        }

    override var oldVirusTestCount: Int
        get() = getValue(PREF_VIRUS_RESULTS_COUNT) ?: -1
        set(value) {
            sharedPreferences.edit { putInt(PREF_VIRUS_RESULTS_COUNT, value) }
        }

    override var virusVaccineStatesResponse: String
        get() = getValue(PREF_VIRUS_VACCINE_STATES) ?: ""
        set(value) {
            sharedPreferences.edit { putString(PREF_VIRUS_VACCINE_STATES, value) }
        }

    override var tempPhoneNumber: String?
        get() = getValue(PREF_PHONE_NUMBER_FROM_YAKEEN)
        set(value) {
            sharedPreferences.edit().putString(PREF_PHONE_NUMBER_FROM_YAKEEN, value).apply()
        }

    override var phoneNumber: String?
        get() = getValue(PREF_PHONE_NUMBER_FROM_SEHHATY) ?: null
        set(value) {
            sharedPreferences.edit().putString(PREF_PHONE_NUMBER_FROM_SEHHATY, value).apply()
        }

    override var iAMRedirectionUrl: String?
        get() = sharedPreferences.getString(
            PREF_IAM_REDIRECTION_URL, null
        )
        set(value) {
            sharedPreferences.edit().putString(
                PREF_IAM_REDIRECTION_URL, value
            ).apply()
        }

    override var iAmSessionId: String?
        get() = sharedPreferences.getString(
            PREF_IAM_SESSION_ID, null
        )
        set(value) {
            sharedPreferences.edit().putString(
                PREF_IAM_SESSION_ID, value
            ).apply()
        }

    override var userHash: String?
        get() = sharedPreferences.getString(
            PREF_USER_HASH, null
        )
        set(value) {
            sharedPreferences.edit().putString(
                PREF_USER_HASH, value
            ).apply()
        }

    override var isOver40thWeeksPopUpIsShown: Boolean
        get() = sharedPreferences.getBoolean(
            JUST_LOGGED_IN_AND_PREGNANT_OVER_40TH_WEEKS, false
        )
        set(value) {
            sharedPreferences.edit().putBoolean(JUST_LOGGED_IN_AND_PREGNANT_OVER_40TH_WEEKS, value)
                .commit()
        }

    override var themePreference: String?
        get() = getValue(PREF_APP_THEME)
        set(value) {
            sharedPreferences.edit { putString(PREF_APP_THEME, value) }
        }

    override var appAppearancePreference: String?
        get() = getValue(PREF_APP_APPEARANCE)
        set(value) {
            sharedPreferences.edit { putString(PREF_APP_APPEARANCE, value) }
        }

    override var userDOB: String?
        get() = getValue(PREF_USER_DOB)
        set(value) {
            sharedPreferences.edit { putString(PREF_USER_DOB, value) }
        }
    override var healthId: String?
        get() = getValue(UserKeys.HEALTH_ID)
        set(value) {
            set(UserKeys.HEALTH_ID, value)
        }

    override var dependentNationalID: String?
        get() = getValue(PREF_DEPENDENT_NATIONAL_ID)
        set(value) = sharedPreferences.edit().putString(PREF_DEPENDENT_NATIONAL_ID, value).apply()

    override var dependentDOB: String?
        get() = getValue(PREF_DEPENDENT_DOB)
        set(value) {
            sharedPreferences.edit { putString(PREF_DEPENDENT_DOB, value) }
        }

    override var isUser: Boolean
        get() = getValue(PREF_IS_USER) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_IS_USER, value).apply()
        }
    override var isUnderAge: Boolean
        get() = getValue(UserKeys.IS_UNDERAGE) ?: false
        set(value) {
            set(UserKeys.IS_UNDERAGE, value)
        }
    override var isVerified: Boolean
        get() = getValue(UserKeys.IS_VERIFIED) ?: false
        set(value) {
            set(UserKeys.IS_VERIFIED, value)
        }
    override var gender: Int
        get() = getValue(UserKeys.GENDER) ?: -1
        set(value) {
            set(UserKeys.GENDER, value)
        }
    override var cityId: Long?
        get() = getValue(UserKeys.CITY_ID) ?: -1L
        set(value) = set(UserKeys.CITY_ID, value)
    override var districtId: Long?
        get() = getValue(UserKeys.DISTRICT_ID) ?: -1L
        set(value) = set(UserKeys.DISTRICT_ID, value)
    override var cityName: String?
        get() = getValue(UserKeys.CITY_NAME)
        set(value) = set(UserKeys.CITY_NAME, value)
    override var districtName: String?
        get() = getValue(UserKeys.DISTRICT_NAME)
        set(value) = set(UserKeys.DISTRICT_NAME, value)
    override var isConfirmedNationalAddress: Boolean
        get() = getValue(UserKeys.IS_CONFIRM_NATIONAL_ADDRESS) ?: false
        set(value) = set(UserKeys.IS_CONFIRM_NATIONAL_ADDRESS, value)
    override var nationalityCode: String?
        get() = getValue(UserKeys.NATIONALITY_CODE)
        set(value) = set(UserKeys.NATIONALITY_CODE, value)
    override var nationalityNameEn: String?
        get() = getValue(UserKeys.NATIONALITY_NAME_EN)
        set(value) = set(UserKeys.NATIONALITY_NAME_EN, value)
    override var nationalityNameAr: String?
        get() = getValue(UserKeys.NATIONALITY_NAME_AR)
        set(value) = set(UserKeys.NATIONALITY_NAME_AR, value)

    override var shouldViewHealthSummaryFeedbackDialog: Boolean?
        get() {
            // Set it and get it as STRING, because we can't set/get nullable boolean
            return when (getValue(PREF_SHOULD_SHOW_HEALTHSUMMARY_FEEDBACK_DIALOG_V2) as? String?) {
                "true" -> true
                "false" -> false
                else -> null
            }
        }
        set(value) {
            val str = when (value) {
                true -> "true"
                false -> "false"
                else -> null
            }
            sharedPreferences.edit()
                .putString(PREF_SHOULD_SHOW_HEALTHSUMMARY_FEEDBACK_DIALOG_V2, str).apply()
        }

    override var dashboardBanner: String?
        get() = getValue(PREF_DASHBOARD_BANNER)
        set(value) {
            sharedPreferences.edit().putString(PREF_DASHBOARD_BANNER, value)
                .apply()
        }

    override var userLocator: String?
        get() = getValue(PREF_USER_LOCATOR)
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_LOCATOR, value)
                .apply()
        }
    override var userClusterId: String?
        get() = getValue(PREF_USER_CLUSTER_ID)
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_CLUSTER_ID, value)
                .apply()
        }

    override var userClusterName: String?
        get() = getValue(PREF_USER_CLUSTER_NAME)
        set(value) {
            sharedPreferences.edit().putString(PREF_USER_CLUSTER_NAME, value)
                .apply()
        }
    override var tempPassportNumber: String?
        get() = getValue(PREF_TEMP_PASSPORT_NUMBER)
        set(value) {
            sharedPreferences.edit().putString(PREF_TEMP_PASSPORT_NUMBER, value).apply()
        }

    override var tempNationalityId: Int?
        get() = getValue(PREF_TEMP_NATIONALITY_ID) ?: -1
        set(value) {
            sharedPreferences.edit().putInt(PREF_TEMP_NATIONALITY_ID, value ?: -1).apply()
        }

    override var isVisitor: Boolean
        get() = getValue(PREF_TEMP_IS_VISITOR) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_TEMP_IS_VISITOR, value).apply()
        }

    override var showNotificationCenterDialog: Boolean
        get() = getValue(PREF_NOTIFICATION) ?: false
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_NOTIFICATION, value).apply()
        }

    override fun getCacheMeterTimestamp(key: String, default: Long): Long =
        getValue(key) ?: default

    override fun setCacheMeterTimestamp(key: String, value: Long) =
        sharedPreferences.edit().putLong(key, value).apply()

    override fun removeCacheMeterTimestamp(key: String) =
        sharedPreferences.edit().remove(key).apply()


    override var healthCareCenterId: Int?
        get() = getValue(PREF_HEALTH_CARE_ID) ?: -1
        set(value) = sharedPreferences.edit().putInt(PREF_HEALTH_CARE_ID, value ?: -1).apply()

    override var healthSummeryToolTipVisitedId: String?
        get() = appConfigPreference.getString(PREF_HEALTH_SUMMARY_TIP_TOOL_VISITED_ID, null)
        set(value) {
            appConfigPreference.edit().putString(PREF_HEALTH_SUMMARY_TIP_TOOL_VISITED_ID, value)
                .apply()
        }

    override var onboardingVisitedVersion: String?
        get() = appConfigPreference.getString(PREF_ONBOARDING_VISITED_VERSION, null)
        set(value) {
            appConfigPreference.edit().putString(PREF_ONBOARDING_VISITED_VERSION, value)
                .apply()
        }

    override fun clear() {
        val keysToKeep = setOf(PREF_APP_THEME, PREF_SHOW_HEALTH_SUMMARY_TIP_TOOL, PREF_APP_APPEARANCE)
        sharedPreferences.edit().apply {
            sharedPreferences.all.keys.filterNot { it in keysToKeep }.forEach { remove(it) }
            apply()
        }
    }

    private inline fun <reified T> getValue(key: String): T? = sharedPreferences.all[key] as? T?

    private fun <T> set(key: String, value: T?) {
        when (value) {
            is Boolean -> sharedPreferences.edit().putBoolean(key, value).apply()
            is Int -> sharedPreferences.edit().putInt(key, value).apply()
            is Long -> sharedPreferences.edit().putLong(key, value).apply()
            is Float -> sharedPreferences.edit().putFloat(key, value).apply()
            is String? -> sharedPreferences.edit().putString(key, value).apply()
            else -> throw PerfsException("value type invalid for appPrefs")
        }
    }

    class PerfsException(message: String) : Exception()

    companion object {
        private const val ENGLISH = "en"
        private const val ARABIC = "ar"
        private val supportedLang = arrayOf(ARABIC, ENGLISH)
        private const val app_preferences = "SEHHATY_PREFERENCES"
        private const val APP_PREF_CONFIG = "APP_PREF_CONFIG"

        private const val PREF_ACCESS_TOKEN = "pref_access_token"
        private const val PREF_EXPIRED_ACCESS_TOKEN = "pref_expired_access_token"
        private const val PREF_REFRESH_TOKEN = "pref_refresh_token"
        private const val PREF_NATIONAL_ID = "pref_national_id"
        private const val PREF_HEALTH_CARE_ID = "pref_healthcare_id"
        private const val PREF_TEMP_IDENTIFIER = "pref_temp_identifier"
        private const val PREF_TEMP_IDENTIFIER2 = "pref_temp_identifier2"
        private const val PREF_TEMP_NATIONAL_ID = "pref_temp_national_id"
        private const val PREF_TEMP_PASSWORD = "pref_temp_password"
        private const val PREF_TEMP_USER_KEY = "pref_temp_user_key"
        private const val PREF_LOCALE = "pref_locale"
        private const val PREF_LOGGED_IN = "pref_logged_in"
        private const val PREF_SHOW_HEALTH_SUMMARY_TIP_TOOL = "PREF_SHOW_HEALTH_SUMMARY_TIP_TOOL"
        private const val PREF_SHOW_EATIZAZ_PRIVACY = "PREF_SHOW_EATIZAZ_PRIVACY"
        private const val PREF_IS_USER_SKIP_EMSH_INTRO = "pref_is_user_skip_intro"
        private const val PREF_FORCE_UPDATE = "pref_force_update"
        private const val PREF_NORMAL_UPDATE = "pref_normal_update"
        private const val PREF_BMI_TIMESTAMP = "pref_bmi_timestamp"
        private const val PREF_SUBMIT_DAILY_SURVEY = "tetamman_daily_survey"
        private const val PREF_IS_ACTIVE_QUARANTINE = "tetamman_is_in_isolation"
        private const val PREF_SHOW_IN_APP_HTTP_INTERCEPTOR = "pref_show_in_app_http_interceptor"
        private const val PREF_VIRUS_STATUS = "pref_virus_status"
        private const val PREF_VIRUS_VACCINE = "pref_virus_vaccine"
        private const val PREF_VIRUS_VACCINE_STATES = "pref_virus_vaccine_status"
        private const val PREF_VIRUS_TOKEN = "pref_virus_token"
        private const val PREF_VIRUS_RESULTS_COUNT = "prefs_virus_results_count"
        private const val PREF_PHONE_NUMBER_FROM_YAKEEN = "pref_phone_number_from_yakeen"
        private const val PREF_PHONE_NUMBER_FROM_SEHHATY = "pref_phone_number_from_sehhaty"
        private const val PREF_IAM_REDIRECTION_URL = "pref_iam_redirection_url"
        private const val PREF_IAM_SESSION_ID = "pref_iam_session_id"
        private const val PREF_USER_HASH = "pref_user_hash"
        private const val JUST_LOGGED_IN_AND_PREGNANT_OVER_40TH_WEEKS =
            "pref_pregnant_over_40th_weeks"
        private const val PREF_USER_DOB = "pref_user_dob"
        private const val PREF_DEPENDENT_NATIONAL_ID = "pref_dependent_national_id"
        private const val PREF_DEPENDENT_DOB = "pref_dependent_dob"
        private const val PREF_IS_USER = "pref_is_user"
        private const val PREF_SHOULD_SHOW_HEALTHSUMMARY_FEEDBACK_DIALOG_V2 =
            "pref_should_show_healthsummary_feedback_dialog_v2"
        private const val PREF_USER_NAME = "pref_user_name"
        private const val PREF_USER_FULL_NAME = "pref_user_full_name"
        private const val PREF_DASHBOARD_BANNER = "pref_dashboard_banner"
        private const val PREF_USER_LOCATOR = "pref_user_locator"
        private const val PREF_USER_CLUSTER_ID = "pref_user_cluster_id"
        private const val PREF_USER_CLUSTER_NAME = "pref_user_cluster_name"

        private const val PREF_TEMP_PASSPORT_NUMBER = "pref_temp_passport_number"
        private const val PREF_TEMP_NATIONALITY_ID = "pref_temp_nationality_id"
        private const val PREF_TEMP_IS_VISITOR = "PREF_TEMP_IS_VISITOR"

        private const val PREF_NOTIFICATION = "pref_notification"
        private const val PREF_IS_FCM_TOKEN_SENT = "pref_is_fcm_sent"
        private const val PREF_STEPS_PERMISSION = "pref_steps_permission"
        private const val PREF_ANALYTIC_ID = "PREF_ANALYTIC_ID"

        private const val PREF_ALLOW_PROFILE_UPDATE = "PREF_ALLOW_PROFILE_UPDATE"
        private const val PREF_PROFILE_UPDATED_AT = "PREF_PROFILE_UPDATED_AT"

        private const val PREF_HEALTH_SUMMARY_TIP_TOOL_VISITED_ID = "PREF_HEALTH_SUMMARY_TIP_TOOL_VISITED_ID"
        private const val PREF_ONBOARDING_VISITED_VERSION = "PREF_ONBOARDING_VISITED_VERSION"

        private const val PREF_APP_THEME = "pref_app_theme"

        private const val PREF_APP_APPEARANCE= "pref_app_appearance"

    }
}
