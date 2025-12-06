package org.example.firstcmpapp

import android.util.Log

actual fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}