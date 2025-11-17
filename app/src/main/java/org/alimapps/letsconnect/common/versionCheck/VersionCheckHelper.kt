package org.alimapps.letsconnect.common.versionCheck

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.alimapps.letsconnect.core.state.Event
import org.alimapps.letsconnect.di.coroutines.ApplicationScope
import com.github.glwithu06.semver.Semver
import com.lean.sehhaty.BuildConfig
import com.lean.sehhaty.common.state.Event
import com.lean.sehhaty.remoteconfig.RemoteConfigSource
import com.lean.sehhaty.session.IAppPrefs
import com.lean.sehhaty.utils.di.coroutines.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VersionCheckHelper @Inject constructor(
    val appPrefs: IAppPrefs,
    val remoteConfigSource: RemoteConfigSource,
    @ApplicationScope val applicationScope: CoroutineScope,
) {

    // Live data holding checkVersion.ForceUpdate response
    private val _forceUpdate = MutableLiveData<Event<Boolean>>()
    val forceUpdate: LiveData<Event<Boolean>> = _forceUpdate

    // Flag to show/hide progress indicator
    private val _loadingState = MutableLiveData<Boolean>().apply { value = true }
    val loadingState: LiveData<Boolean> = _loadingState

    fun checkVersion() {
        applicationScope.launch {
            // Read current store version code and compare it with the current running version
            var storeVersionString = remoteConfigSource.getStringFromJson(
                    RemoteConfigSource.KEY_FORCE_UPDATE,
                    RemoteConfigSource.PARAM_CURRENT_STORE_VERSION
            )
            Log.d(TAG, "Current Store Version: $storeVersionString")

            if (storeVersionString.isEmpty()) {
                storeVersionString = "2.2.1" //default value, same as value in remote_config_defaults.xml
            }

            val storeVersion = Semver(storeVersionString)
            val appVersion = Semver(BuildConfig.VERSION_NAME)

            if (appVersion < storeVersion) {
                Log.d(TAG, "Current Running Version less than Store")
                // if we have update check if it's FORCE or NORMAL update
                val isForceUpdated = remoteConfigSource.getBooleanFromJson(
                        com.lean.sehhaty.remoteconfig.RemoteConfigSource.KEY_FORCE_UPDATE,
                        com.lean.sehhaty.remoteconfig.RemoteConfigSource.PARAM_IS_FORCE_UPDATE
                )
                Log.d(TAG, "Is This ForceUpdate ? = $isForceUpdated")

                if (isForceUpdated) {
                    _forceUpdate.postValue(Event(true))
                } else {
                    _forceUpdate.postValue(Event(false))
                }
            } else {
                Log.d(TAG, "Current Running Version is equal to Store")
                _loadingState.postValue(false)
            }
        }
    }

    companion object {
        private const val TAG = "VersionCheckHelper"
    }
}