package org.alimapps.letsconnect.core.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alimapps.letsconnect.core.common.helper.LanguageHelper
import org.alimapps.letsconnect.core.common.helper.NetworkReceiver
import org.alimapps.letsconnect.core.common.utils.Const
import org.alimapps.letsconnect.core.common.utils.FontsOverride
import org.alimapps.letsconnect.core.data.session.SharedPrefsRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private var networkReceiver: NetworkReceiver? = null
    lateinit var webFormat: SimpleDateFormat

    @Inject
    lateinit var preference: SharedPrefsRepository
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LanguageHelper.wrapper(
                newBase,
                preference.languageCode
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/SanFrancisco_Regular.otf")
        networkReceiver = NetworkReceiver()
        Const.SELECTED_LANGUAGE = preference.languageName
        Const.SELECTED_LANGUAGE_CODE = preference.languageCode
        initDateFormat()
        if (preference.accessToken.isNullOrEmpty()) {
            getFcmDeviceToken()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(networkReceiver, IntentFilter(Const.Action.NETWORK_ACTION))

    }

    @SuppressLint("SimpleDateFormat")
    private fun initDateFormat() {
        webFormat =
            SimpleDateFormat(Const.DATE_TIME_FORMAT_WEB, Locale(Const.SELECTED_LANGUAGE_CODE))
        webFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    /** getting FCM device token */
    private fun getFcmDeviceToken() {
//        FirebaseMessaging.getInstance().token
//            .addOnCompleteListener(
//                OnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        AppLogs.log(
//                            Const.Tags.BASE_ACTIVITY,
//                            "FCM registration token failed >> " + task.exception.toString()
//                        )
//                        return@OnCompleteListener
//                    }
//                    val deviceToken = task.result
//                    AppLogs.log(Const.Tags.BASE_ACTIVITY, "Device Token >> $deviceToken")
//                    preference.putDeviceToken(deviceToken)
//                }
//            )
    }

    fun isUserLogin(): Boolean {
        return preference.isLoggedIn
    }

}
