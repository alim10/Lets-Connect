package org.alimapps.letsconnect.core.event

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventPublisherImpel @Inject constructor() : EventSubscriber, EventPublisher {
    private val _event = Channel<AppEvent>()
    override val event = _event.receiveAsFlow()
    override fun send(event: AppEvent) {
        _event.trySend(event)
    }
}

