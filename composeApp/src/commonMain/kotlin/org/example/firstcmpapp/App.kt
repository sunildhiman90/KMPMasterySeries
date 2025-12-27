package org.example.firstcmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    databaseDriverFactory: DatabaseDriverFactory,
    multiplatformSettingsFactory: MultiplatformSettingsFactory
) {
    MaterialTheme {

        val dbHelper = remember {  DbHelper(databaseDriverFactory) }
        val productsDao = remember { ProductsDaoImpl(dbHelper) }

        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            productsDao.insertProduct("Product 2", 12.0)
        }

        var updated by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .systemBarsPadding()
                .padding(top = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                scope.launch {
                    try {
                        productsDao.insertProduct("Product 2", 12.0)
                        updated = !updated
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                        e.printStackTrace()
                    }

                }
            }) {
                Text("Insert Product")
            }

            var products = remember { mutableStateListOf<ProductItem?>(null) }

            LaunchedEffect(updated) {

                scope.launch {
                    productsDao.selectAllProducts()?.let {
                        println("products size: ${it.size}")
                        products.clear()
                        products.addAll(it)
                    }

//
//                    productsDao.searchProductsByName("Product 3")?.let {
//                        products.clear()
//                        products.addAll(it)
//                    }
                }

            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    16.dp
                )
            ) {
                items(products) { product ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${product?.name} ${product?.price}")

                            Button(onClick = {
                                scope.launch {
                                    productsDao.deleteProductById(product?.id!!)
                                }
                                updated = !updated
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }


        }
    }
}