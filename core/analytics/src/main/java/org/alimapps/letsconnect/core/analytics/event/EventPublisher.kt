package org.alimapps.letsconnect.core.analytics.event

import javax.inject.Singleton

@Singleton
interface EventPublisher {
    fun send(event: AppEvent)
}