package org.example.firstcmpapp.ch8_localDatabaseSqldelight

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.firstcmpapp.CmpappDb
import java.util.Properties

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(
            url = JdbcSqliteDriver.IN_MEMORY,
            properties = Properties().apply {
                put("foreign_keys", "true")
            }
        )
        CmpappDb.Companion.Schema.awaitCreate(driver)
        return driver
    }
}