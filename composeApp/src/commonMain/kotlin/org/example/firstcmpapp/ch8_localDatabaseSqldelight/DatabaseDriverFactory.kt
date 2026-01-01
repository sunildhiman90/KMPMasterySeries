package org.example.firstcmpapp.ch8_localDatabaseSqldelight

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    suspend fun createDriver(): SqlDriver
}

const val DB_FILE_NAME = "cmpappdb.db"