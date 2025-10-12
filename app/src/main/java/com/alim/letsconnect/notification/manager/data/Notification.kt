package com.alim.letsconnect.notification.manager.data

class Notification(
    val reference: String?,
    val objectId: String?,
    val targetNationalId: String?,
    val title: String?,
    val body: String?,
    val action: String?,
    val extraData: Map<String?, String?>,
    val event: String?,
    val entity: String?
)