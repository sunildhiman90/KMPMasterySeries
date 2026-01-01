package org.example.firstcmpapp

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory


class DbHelper(
    private val driverFactory: DatabaseDriverFactory
) {
    private var db: CmpappDb? = null


    private val mutex = Mutex()

    suspend fun <Result: Any> withDatabase(block: suspend (CmpappDb) -> Result?): Result? {
        return mutex.withLock {

            if (db == null) {
                db = CmpappDb(driverFactory.createDriver())
            }

            return@withLock block(db!!)
        }
    }

}