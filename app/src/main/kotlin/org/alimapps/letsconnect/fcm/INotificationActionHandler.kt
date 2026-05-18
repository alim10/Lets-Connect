package org.alimapps.letsconnect.fcm

interface INotificationActionHandler {
    suspend fun setRatingSurvey(url: String?)
    suspend fun setTelehealthSurvey(sessionItemID: String?)
}