package org.alimapps.letsconnect.core.common.event

import javax.inject.Singleton

@Singleton
interface EventPublisher {
    fun send(event: AppEvent)
}