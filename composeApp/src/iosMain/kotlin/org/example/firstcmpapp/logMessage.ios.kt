package org.example.firstcmpapp

import platform.Foundation.NSLog

actual fun logMessage(tag: String, msg: String) {
    NSLog("$tag: $msg")
}