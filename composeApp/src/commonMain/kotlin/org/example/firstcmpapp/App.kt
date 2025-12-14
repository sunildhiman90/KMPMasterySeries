package org.example.firstcmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    multiplatformSettingsFactory: MultiplatformSettingsFactory
) {
    MaterialTheme {

        val appPreferences = AppPreferencesImpl(multiplatformSettingsFactory.getSettings())

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                appPreferences.putString("name", "CodingAmbitions")
            }) {
                Text("Save In Pref")
            }

            Text("Get From Pref: ${appPreferences.getString("name", "")}")

        }
    }
}