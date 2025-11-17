package org.alimapps.letsconnect.core.event

import javax.inject.Singleton

@Singleton
interface EventPublisher {
    fun send(event: AppEvent)
}