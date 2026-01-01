package org.example.firstcmpapp.ch8_localDatabaseSqldelight

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.firstcmpapp.CmpappDb

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual suspend fun createDriver(): SqlDriver {
        val schema = CmpappDb.Companion.Schema
        return AndroidSqliteDriver(
            schema = schema.synchronous(),
            context = context,
            name = DB_FILE_NAME,
            callback = object : AndroidSqliteDriver.Callback(schema.synchronous()) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}