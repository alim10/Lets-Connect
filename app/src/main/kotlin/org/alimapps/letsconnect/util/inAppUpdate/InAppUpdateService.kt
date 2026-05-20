package org.alimapps.letsconnect.util.inAppUpdate

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.alimapps.letsconnect.core.common.logging.Logger
import org.alimapps.letsconnect.core.common.utils.Const.IN_APP_UPDATE_FLEXIBLE_RESULT_CODE
import org.alimapps.letsconnect.core.common.utils.Const.IN_APP_UPDATE_IMMEDIATE_RESULT_CODE
import org.alimapps.letsconnect.core.common.utils.isGmsAvailable
import java.lang.ref.WeakReference

class InAppUpdateService : LifecycleObserver,
    LifecycleEventObserver {

    private var appUpdateManager: AppUpdateManager? = null
    private var activity: WeakReference<AppCompatActivity>? = null
    private lateinit var flexibleUpdateListener: InstallStateUpdatedListener

    fun initInAppUpdateManager(mActivity: WeakReference<AppCompatActivity>) {
        if (isGmsAvailable(mActivity.get())) {
            activity = mActivity
            mActivity.get()?.lifecycle?.addObserver(this)
            appUpdateManager = activity?.get()?.let { AppUpdateManagerFactory.create(it) }
        }
    }

    fun checkForInAppUpdates() {
        appUpdateManager?.appUpdateInfo
            ?.addOnCompleteListener { Logger.d("UpdateManager just completed update checking.") }
            ?.addOnSuccessListener { updateInfo ->
                Logger.d("UpdateManager just completed and succeed in update checking.\n$updateInfo")
                when {
                    updateInfo.updateNotAvailable() -> {
                        Logger.d("No available update right now")
                        return@addOnSuccessListener
                    }

                    updateInfo.isImmediateUpdate() -> {
                        Logger.d("We've got an immediate update")
                        updateInfo.startUpdateWithType(AppUpdateType.IMMEDIATE)
                    }

                    updateInfo.isFlexibleUpdate() -> {
                        Logger.d("We've got flexible update")
                        monitorFlexibleUpdate(updateInfo)
                    }
                }
            }
            ?.addOnFailureListener { Logger.d("UpdateManager failed to check updates with reason\n${it.message}") }
    }

    private fun AppUpdateInfo.startUpdateWithType(
        @AppUpdateType type: Int,
    ) {
        runCatching {
            appUpdateManager?.startUpdateFlowForResult(
                this,
                type,
                activity?.get()!!,
                getRequestCode(type)
            )
        }.getOrElse {
            FirebaseCrashlytics.getInstance()
                .log("Failed to start inAppUpdate because of ${it.message}")
        }
    }

    private fun monitorFlexibleUpdate(updateInfo: AppUpdateInfo) {
        flexibleUpdateListener = InstallStateUpdatedListener { state ->
            when (state.installStatus()) {
                InstallStatus.DOWNLOADING -> {
                    Logger.d("Flexible update DOWNLOADING ${state.bytesDownloaded()} of ${state.totalBytesToDownload()}")
                }

                InstallStatus.DOWNLOADED -> {
                    Logger.d("Flexible update DOWNLOADED")
                }

                InstallStatus.INSTALLED -> {
                    Logger.d("Flexible update INSTALLED")
                }

                else -> Logger.d("Unknown install state $state.")
            }
        }
        appUpdateManager?.registerListener(flexibleUpdateListener)
        updateInfo.startUpdateWithType(AppUpdateType.FLEXIBLE)
    }

    private fun getRequestCode(@AppUpdateType type: Int) =
        when (type) {
            AppUpdateType.IMMEDIATE -> IN_APP_UPDATE_IMMEDIATE_RESULT_CODE
            AppUpdateType.FLEXIBLE -> IN_APP_UPDATE_FLEXIBLE_RESULT_CODE
            else -> -1
        }

    fun onActivityResult(requestCode: Int, resultCode: Int, onOptionalCanceled: () -> Unit) {
        when (requestCode) {
            IN_APP_UPDATE_IMMEDIATE_RESULT_CODE -> {
                if (resultCode != AppCompatActivity.RESULT_OK) {
                    Logger.d("inAppUpdate failed with code: $resultCode")
                    activity?.get()?.finishAffinity()
                }
            }

            IN_APP_UPDATE_FLEXIBLE_RESULT_CODE -> {
                if (resultCode != AppCompatActivity.RESULT_OK) {
                    Logger.d("IN_APP_UPDATE_FLEXIBLE_RESULT_CODE")
                    onOptionalCanceled.invoke()
                }
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

    }

}

private fun AppUpdateInfo.isImmediateUpdate() =
    updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
        isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)

private fun AppUpdateInfo.isFlexibleUpdate() =
    updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
        isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)

private fun AppUpdateInfo.updateNotAvailable() =
    updateAvailability() == UpdateAvailability.UPDATE_NOT_AVAILABLE