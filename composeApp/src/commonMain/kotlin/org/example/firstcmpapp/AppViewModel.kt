package org.example.firstcmpapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.List

sealed class AppEvent {
    data class InsertProduct(val name: String, val price: Double): AppEvent()
    data class DeleteProduct(val id: Long): AppEvent()
    data object LoadProducts: AppEvent()
    data class OnProductClick(val id: Long): AppEvent()
}

data class AppUiState(
    val products: List<ProductItem> = emptyList(),
    val isLoading: Boolean = false
)


class AppViewModel(
    val productsDao: ProductsDao
): ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AppEvent) {
        when(event) {
            is AppEvent.InsertProduct -> {
                viewModelScope.launch {
                    productsDao.insertProduct(event.name, event.price)
                }
            }

            is AppEvent.DeleteProduct -> {
                viewModelScope.launch {
                    productsDao.deleteProductById(event.id)
                }
            }

            is AppEvent.LoadProducts -> {
                viewModelScope.launch {
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = true
//                    )
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                    productsDao.selectAllProducts()?.let {
                        _uiState.value = _uiState.value.copy(
                            products = it,
                            isLoading = false
                        )
                    }
                }
            }

            else -> {

            }
        }
    }


}