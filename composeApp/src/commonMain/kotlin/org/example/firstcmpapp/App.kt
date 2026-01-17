package org.example.firstcmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Composable
@Preview
fun App() {

    MaterialTheme {


        val viewModel = koinInject<AppViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        var updated by remember { mutableStateOf(false) }

        LaunchedEffect(updated) {
            viewModel.onEvent(AppEvent.LoadProducts)
        }

        val onEvent: (AppEvent) -> Unit = {
            viewModel.onEvent(it)
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            ProductList(uiState = uiState, onEvent = onEvent, onUpdated = {
                updated = !updated
            })
        }
    }


}

@Composable
private fun ProductList(
    onEvent: (AppEvent) -> Unit,
    onUpdated: () -> Unit,
    uiState: AppUiState
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            try {
                onEvent(
                    AppEvent.InsertProduct(
                        name = "Product 2",
                        price = 12.0
                    )
                )
                onUpdated()
            } catch (e: Exception) {
                println("Error: ${e.message}")
                e.printStackTrace()
            }
        }) {
            Text("Insert Product")
        }

        var products = uiState.products

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
                            product.id?.let {
                                onEvent(AppEvent.DeleteProduct(it))
                            }
                            onUpdated()
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}