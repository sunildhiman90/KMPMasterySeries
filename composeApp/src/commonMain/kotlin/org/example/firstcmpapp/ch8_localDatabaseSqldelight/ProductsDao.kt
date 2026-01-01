package org.example.firstcmpapp

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull

interface ProductsDao {
    suspend fun insertProduct(name: String, price: Double)
    suspend fun selectAllProducts(): List<ProductItem>?
    suspend fun selectProductById(id: Long): ProductItem?
    suspend fun deleteProductById(id: Long)
    suspend fun searchProductsByName(name: String): List<ProductItem>?
}

class ProductsDaoImpl(
    private val dbHelper: DbHelper
): ProductsDao {

    override suspend fun insertProduct(name: String, price: Double) {
        dbHelper.withDatabase { db ->
            try {
                println("Attempting to insert product...")
                db.productQueries.insertProduct(name, price)
                println("Successfully inserted product.")
            } catch (e: Exception) {
                println("Error inserting product: ${e.message}") // This will tell you exactly what's wrong
                // e.printStackTrace() // Use this for a full stack trace
            }
        }
    }

    override suspend fun selectAllProducts(): List<ProductItem>? {
        return dbHelper.withDatabase { db ->
            db.productQueries.selectAll().awaitAsList().map {
                ProductItem(it.id, it.name, it.price)
            }
        }
    }

    override suspend fun selectProductById(id: Long): ProductItem? {
        return dbHelper.withDatabase { db ->
            db.productQueries.selectProductById(id).awaitAsOneOrNull()?.let {
                ProductItem(it.id, it.name, it.price)
            }
        }
    }

    override suspend fun deleteProductById(id: Long) {
        dbHelper.withDatabase { db ->
            db.productQueries.deleteProductById(id)
        }
    }

    override suspend fun searchProductsByName(name: String): List<ProductItem>? {
        return dbHelper.withDatabase { db ->
            db.productQueries.searchProduct(name).awaitAsList().map {
                ProductItem(it.id, it.name, it.price)
            }
        }
    }


}