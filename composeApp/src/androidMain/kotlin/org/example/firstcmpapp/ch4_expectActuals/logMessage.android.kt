package org.example.firstcmpapp.ch4_expectActuals

import android.util.Log

actual fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}
