package org.alimapps.letsconnect.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import org.alimapps.letsconnect.core.common.session.AppPrefsRepository
import org.alimapps.letsconnect.presentation.ui.MainActivity
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private var retryProviderInstall: Boolean = false

    // Declare var of CheckVersion view model, easy to move logic to the other fragments
//    @Inject
//    lateinit var versionCheckHelper: VersionCheckHelper



    @Inject
    lateinit var appPrefs: AppPrefsRepository

//    @Inject
//    lateinit var notificationsManager: NotificationsManager


    private val viewModel: SplashViewModel by viewModels()
//    private val inAppUpdateService by lazy { InAppUpdateService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(Looper.getMainLooper())

        if(appPrefs.isLoggedIn)
        handler.postDelayed({
            launchActivity()
        }, 1000)

    }

   private fun launchActivity() {
        val landingIntent = Intent(this, MainActivity::class.java)
        org.alimapps.letsconnect.presentation.ui.startActivity(landingIntent)
    }
}
