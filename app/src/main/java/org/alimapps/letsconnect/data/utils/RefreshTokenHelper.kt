package org.alimapps.letsconnect.data.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.alimapps.letsconnect.core.state.Event
import org.alimapps.letsconnect.core.state.StateData
import org.alimapps.letsconnect.di.coroutines.ApplicationScope
//import com.lean.sehhaty.common.state.StateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class RefreshTokenHelper @Inject constructor(
    @ApplicationScope val applicationScope: CoroutineScope
) {

    // Update state of refresh token operation
    private val _tokenRefreshed = MutableLiveData<Event<Boolean>>()
    val tokenRefreshed: LiveData<Event<Boolean>> = _tokenRefreshed

    private var refreshTokenJob: Job? = null

    fun refreshToken() {
        // return if this job is active because we don't need duplicate calls
        if (isRunning()) return

        refreshTokenJob = applicationScope.launch {
            // Now we don't need the return back Boolean but we can use it later
            val syncRefreshToken = userRepository.refreshTokenBackground()

            when (syncRefreshToken.status) {
                StateData.DataStatus.SUCCESS -> {
                    _tokenRefreshed.postValue(Event(true))
                }
                else -> {
                    _tokenRefreshed.postValue(Event(false))
                }
            }
        }
    }

    fun isRunning(): Boolean = refreshTokenJob != null && refreshTokenJob!!.isActive
}