package com.alim.letsconnect.core.event

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface EventSubscriber {
    val event: Flow<AppEvent>
}