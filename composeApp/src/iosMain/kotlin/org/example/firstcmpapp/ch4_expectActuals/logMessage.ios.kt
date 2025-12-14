package org.example.firstcmpapp.ch4_expectActuals

import platform.Foundation.NSLog

actual fun logMessage(tag: String, msg: String) {
    NSLog("$tag: $msg")
}