package org.example.firstcmpapp

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = createDefaultWebWorkerDriver()
        CmpappDb.Schema.awaitCreate(driver)
        driver.execute(null, "PRAGMA foreign_keys = ON", 0)
        return driver
    }
}