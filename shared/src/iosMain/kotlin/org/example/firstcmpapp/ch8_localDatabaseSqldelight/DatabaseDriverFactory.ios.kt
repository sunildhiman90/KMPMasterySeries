package org.example.firstcmpapp.ch8_localDatabaseSqldelight

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import org.example.firstcmpapp.CmpappDb

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = CmpappDb.Companion.Schema.synchronous(),
            name = DB_FILE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig =
                        DatabaseConfiguration.Extended(
                            foreignKeyConstraints = true
                        )
                )
            }
        )
    }
}