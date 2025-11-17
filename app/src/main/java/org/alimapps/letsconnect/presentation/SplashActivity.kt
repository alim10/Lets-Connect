package org.alimapps.letsconnect.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import org.alimapps.letsconnect.core.navigations.Activities
import org.alimapps.letsconnect.core.navigations.Navigator
import org.alimapps.letsconnect.notification.NotificationsManager
import org.alimapps.letsconnect.utils.InAppUpdateService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private var retryProviderInstall: Boolean = false

    // Declare var of CheckVersion view model, easy to move logic to the other fragments
    @Inject
    lateinit var versionCheckHelper: VersionCheckHelper

    @Inject
    lateinit var refreshTokenHelper: RefreshTokenHelper


    @Inject
    lateinit var appPrefs: IAppPrefs

    @Inject
    lateinit var notificationsManager: NotificationsManager

    @Inject
    lateinit var provider: Navigator.Provider

    private val viewModel: SplashViewModel by viewModels()
    private val inAppUpdateService by lazy { InAppUpdateService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            provider.getActivities(Activities.MainActivity)
        }, 1000)

    }
}
