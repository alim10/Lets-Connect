//package org.alimapps.letsconnect.common.versionCheck
//
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import org.alimapps.letsconnect.BuildConfig
//import org.alimapps.letsconnect.core.common.session.AppPrefsRepository
//import org.alimapps.letsconnect.di.coroutines.ApplicationScope
//import javax.inject.Inject
//
//
//class VersionCheckHelper @Inject constructor(
//    val appPrefs: AppPrefsRepository,
////    val remoteConfigSource: RemoteConfigSource,
//    @ApplicationScope val applicationScope: CoroutineScope,
//) {
//
//    // Live data holding checkVersion.ForceUpdate response
//    private val _forceUpdate = MutableStateFlow<Boolean?>(null)
//    val forceUpdate = _forceUpdate.asStateFlow()
//
//    // Flag to show/hide progress indicator
//    private val _loadingState = MutableStateFlow(true)
//    val loadingState = _loadingState.asStateFlow()
//
//    fun checkVersion() {
//        applicationScope.launch {
//            // Read current store version code and compare it with the current running version
////            var storeVersionString = remoteConfigSource.getStringFromJson(
////                RemoteConfigSource.KEY_FORCE_UPDATE,
////                RemoteConfigSource.PARAM_CURRENT_STORE_VERSION
////            )
//
//            if (storeVersionString.isEmpty()) {
//                storeVersionString = "2.2.1" //default value, same as value in remote_config_defaults.xml
//            }
//
//            var storeVersionBiggerThanAppVersion = false
//            val storeVersion = storeVersionString.split(".").map { it.toInt() }
//            val appVersion = (BuildConfig.VERSION_NAME).split(".").map { it.toInt() }
//
//            if (storeVersion[0] > appVersion[0]) storeVersionBiggerThanAppVersion = true
//            else if (storeVersion[0] >= appVersion[0] && storeVersion[1] > appVersion[1]) storeVersionBiggerThanAppVersion = true
//            else if (storeVersion[0] >= appVersion[0] && storeVersion[1] >= appVersion[1] && storeVersion[2] > appVersion[2]) storeVersionBiggerThanAppVersion = true
//
//            if (storeVersionBiggerThanAppVersion) {
////                val isForceUpdated = remoteConfigSource.getBooleanFromJson(
////                    RemoteConfigSource.KEY_FORCE_UPDATE,
////                    RemoteConfigSource.PARAM_IS_FORCE_UPDATE
////                )
//
//                _forceUpdate.emit(isForceUpdated)
//            } else _loadingState.emit(false)
//        }
//    }
//
//    companion object {
//        private const val TAG = "VersionCheckHelper"
//    }
//}