package com.app.health.remed

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform