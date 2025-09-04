package com.app.health.remed.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class EventFlow<T> {
    private val channel = Channel<T>(Channel.BUFFERED)

    val flow = channel.receiveAsFlow()

    suspend fun send(event: T) {
        channel.send(event)
    }
}
