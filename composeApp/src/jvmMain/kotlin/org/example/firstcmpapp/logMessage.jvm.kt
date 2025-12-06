package org.example.firstcmpapp

actual fun logMessage(tag: String, msg: String) {
    println("$tag: $msg")
}