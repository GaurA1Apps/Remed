package com.app.health.remed.utils

import kotlinx.datetime.LocalTime

expect object AppLogger {
    fun e(tag: String , message: String, throwable: Throwable? = null)
    fun d(tag: String= "MediMinder", message: String)
    fun i(tag: String, message: String)
}
