package org.example.firstcmpapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.read
import firstcmpapp.composeapp.generated.resources.Res
import firstcmpapp.composeapp.generated.resources.delete
import firstcmpapp.composeapp.generated.resources.insert_product
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase
import org.example.firstcmpapp.helper.LanguageHelper
import org.example.firstcmpapp.model.AppLanguageItem
import org.example.firstcmpapp.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Serializable
data object ProdList

@Serializable
data class Detail(
    val id: Long,
)

enum class AppThemeMode {
    LIGHT,
    DARK,
    SYSTEM
}


val LocalLocalization = staticCompositionLocalOf { AppConstants.EN }

@Composable
fun LocalizedApp(
    language: String = AppConstants.EN,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalLocalization provides language) {
        content()
    }

}


@Composable
@Preview
fun App() {

    //Use from preferences
    val systemDarkTheme = isSystemInDarkTheme()
    val appPreferences = koinInject<AppPreferences>()
    var themeMode by remember {
        mutableStateOf(
            appPreferences.getString(
                "theme",
                AppThemeMode.SYSTEM.name
            )
        )
    }

    var isDarkTheme by remember(themeMode) {
        mutableStateOf(
            when (themeMode) {
                AppThemeMode.DARK.name -> true
                AppThemeMode.SYSTEM.name -> systemDarkTheme
                else -> false
            }
        )
    }

    val languageHelper = koinInject<LanguageHelper>()
    val scope = rememberCoroutineScope()

    var appCurrentLanguageCode by remember { mutableStateOf(appPreferences.getString(AppConstants.LANGUAGE,
        AppConstants.EN)) }

    val onCurrentLanguageChange: (String) -> Unit = {
        appCurrentLanguageCode = it

        scope.launch {
            languageHelper.changeLanguageCode(it)
        }
    }
    LocalizedApp(language = appCurrentLanguageCode) {


        AppTheme(
            darkTheme = isDarkTheme
        ) {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = ProdList) {
                composable<ProdList> {

                    val viewModel = koinInject<AppViewModel>()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    var updated by remember { mutableStateOf(false) }

                    LaunchedEffect(updated) {
                        viewModel.onEvent(AppEvent.LoadProducts)
                    }

                    val onEvent: (AppEvent) -> Unit = {
                        when (it) {
                            is AppEvent.OnProductClick -> {
                                //navController.navigate("detail?id=${it.id}")
                                navController.navigate(Detail(it.id))
                            }

                            else -> {
                                viewModel.onEvent(it)
                            }
                        }
                    }

                    if (uiState.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        ProductList(
                            uiState = uiState,
                            onEvent = onEvent,
                            onUpdated = {
                                updated = !updated
                            },
                            isDarkTheme = isDarkTheme,
                            onThemeToggle = {
                                println("onThemeToggle=$it")
                                val theme =
                                    if (it) AppThemeMode.DARK.name else AppThemeMode.LIGHT.name
                                appPreferences.putString("theme", theme)
                                themeMode = theme
                            },
                            onLanguageChange = { language ->
                                onCurrentLanguageChange(language.code)
                            }
                        )
                    }

                }

                composable<Detail>(
                    //arguments = listOf(navArgument("id") { type = NavType.LongType })
                ) {

                    val id = navController.currentBackStackEntry?.arguments?.read { getLong("id") }
                    val productsDao = koinInject<ProductsDao>()

                    var productDetail by remember {
                        mutableStateOf<ProductItem?>(null)
                    }

                    LaunchedEffect(Unit) {
                        id?.let { prodId ->
                            productDetail = productsDao.selectProductById(prodId.toLong())
                        }
                    }

                    Column(
                        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                            .systemBarsPadding()
                            .padding(top = 16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Row {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(Icons.Default.ArrowBack, contentDescription = null)
                            }
                        }
                        Text("Product Id: ${productDetail?.id}")
                        Text("Product Name: ${productDetail?.name}")
                        Text("Product Price: ${productDetail?.price}")
                    }

                }
            }


        }
    }



}

@Composable
private fun ProductList(
    onEvent: (AppEvent) -> Unit,
    onUpdated: () -> Unit,
    uiState: AppUiState,
    isDarkTheme: Boolean,
    onThemeToggle: (isDark: Boolean) -> Unit,
    onLanguageChange: (AppLanguageItem) -> Unit
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    if (showLanguageDialog) {
        LanguageDialog(
            onDismiss = { showLanguageDialog = false },
            onLanguageSelected = { language ->
                println("Language selected: ${language.name}")
                println("Language selected code: ${language.code}")
                showLanguageDialog = false
                onLanguageChange(language)
            }
        )
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                Text(stringResource(Res.string.insert_product))
            }
            IconButton(onClick = { onThemeToggle(!isDarkTheme) }) {
                val icon = if (isDarkTheme) Icons.Default.Brightness7 else Icons.Default.Brightness4
                Icon(icon, contentDescription = "Theme toggle")
            }
            IconButton(onClick = { showLanguageDialog = true }) {
                Icon(Icons.Default.Language, contentDescription = "Change language")
            }
        }

        val products = uiState.products

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                16.dp
            )
        ) {
            items(products) { product ->
                Card(
                    onClick = {
                        product.id?.let {
                            onEvent(AppEvent.OnProductClick(product.id))
                        }
                    },
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
                            Text(stringResource(Res.string.delete))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LanguageDialog(
    onDismiss: () -> Unit,
    onLanguageSelected: (AppLanguageItem) -> Unit
) {
    val getLanguagesUsecase = koinInject<GetLanguagesUseCase>()
    val languages = getLanguagesUsecase.invoke()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose a language") },
        text = {
            LazyColumn {
                items(languages) { language ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(language.flagDrawable),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp).padding(end = 8.dp)
                        )
                        Text(
                            text = language.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onLanguageSelected(language) }
                                .padding(16.dp)
                        )
                    }

                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
