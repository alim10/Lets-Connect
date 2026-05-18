package org.alimapps.letsconnect.fcm

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationActionHandlerImpl @Inject constructor() : INotificationActionHandler{

    private val _ratingFmsSurvey = Channel<String?>()
    val ratingFmsSurvey = _ratingFmsSurvey.receiveAsFlow()

    private val _telehealthSurvey = Channel<String?>()
    val telehealthSurvey = _telehealthSurvey.receiveAsFlow()


    override suspend fun setRatingSurvey(url: String?) {
        _ratingFmsSurvey.send(url)
    }

    override suspend fun setTelehealthSurvey(sessionItemID: String?) {
        _telehealthSurvey.send(sessionItemID)
    }
}