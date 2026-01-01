package org.example.firstcmpapp.ch8_localDatabaseSqldelight

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import org.example.firstcmpapp.CmpappDb

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = createDefaultWebWorkerDriver()
        CmpappDb.Companion.Schema.awaitCreate(driver)
        driver.execute(null, "PRAGMA foreign_keys = ON", 0)
        return driver
    }
}