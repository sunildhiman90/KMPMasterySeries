package org.example.firstcmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                databaseDriverFactory = org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory(
                    this@MainActivity
                ),
                multiplatformSettingsFactory = org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory(this))
        }
    }
}
