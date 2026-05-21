package org.alimapps.letsconnect.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.notification.manager.NotificationsManager
import org.alimapps.letsconnect.presentation.ui.MainActivity
import org.alimapps.letsconnect.util.inAppUpdate.InAppUpdateService
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private var retryProviderInstall: Boolean = false

    // Declare var of CheckVersion view model, easy to move logic to the other fragments
//    @Inject
//    lateinit var versionCheckHelper: VersionCheckHelper

    @Inject
    lateinit var appPrefs: SharedPrefsRepository

    @Inject
    lateinit var notificationsManager: NotificationsManager


    private val viewModel: SplashViewModel by viewModels()
    private val inAppUpdateService by lazy { InAppUpdateService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!appPrefs.isLoggedIn)
        lifecycleScope.launch {
            delay(3000)
            launchActivity()
        }
    }

   private fun launchActivity() {
        val landingIntent = Intent(this, MainActivity::class.java)
        startActivity(landingIntent)
       finish()
    }

    override fun onResume() {
        super.onResume()


        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }
}
