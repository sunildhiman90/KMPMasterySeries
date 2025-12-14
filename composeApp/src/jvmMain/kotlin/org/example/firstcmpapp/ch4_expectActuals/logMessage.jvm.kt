package org.example.firstcmpapp.ch4_expectActuals

actual fun logMessage(tag: String, msg: String) {
    println("$tag: $msg")
}