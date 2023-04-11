package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.order_feature.presentation.components.ConfirmOrderDialog
import com.example.orderappcleanarchitecture.order_feature.presentation.components.ProductUiListItem
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseProductScreen(
    state: ChooseProductsState,
    uiEvent: SharedFlow<ChooseProductsUiEvent>,
    onEvent: (ChooseProductsEvent) -> Unit,
    onNavigateToStartScreen: () -> Unit,
    onNavigateBack: () -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is ChooseProductsUiEvent.ShowSnackbar -> {
                    launch {
                        snackbarHostState.showSnackbar(uiEvent.message)
                    }
                }
                is ChooseProductsUiEvent.OrderConfirmed -> {
                    onNavigateToStartScreen()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_products))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(
                                id = R.string.desc_navigate_back
                            )
                        )
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ChooseProductsEvent.OnShowConfirmOrderDialog)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = stringResource(R.string.desc_show_confirm_dialog)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.productSearchQuery,
                onValueChange = { query ->
                    onEvent(ChooseProductsEvent.OnSearchProductQueryChange(query))
                },
                maxLines = 1,
                label = {
                    Text(text = stringResource(R.string.label_search_product))
                }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    state.productsToShow,
                    key = { it.productId }
                ) { productListItem ->
                    ProductUiListItem(
                        productListItem = productListItem,
                        onPlusClick = {
                            if (state.arePlusAndMinusEnabled) {
                                onEvent(ChooseProductsEvent.OnItemPlusClick(productListItem.productId))
                            }
                        },
                        onMinusClick = {
                            if (state.arePlusAndMinusEnabled) {
                                onEvent(ChooseProductsEvent.OnItemMinusClick(productListItem.productId))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                onEvent(ChooseProductsEvent.OnListItemClick(productListItem.productId))
                            }
                            .padding(15.dp)
                    )
                }
            }
        }
    }

    if (state.isConfirmOrderDialogShown) {
        ConfirmOrderDialog(
            selectedProducts = state.selectedProducts,
            onDismiss = {
                onEvent(ChooseProductsEvent.OnDismissConfirmOrderDialog)
            },
            onConfirm = {
                onEvent(ChooseProductsEvent.OnConfirmOrder)
            }
        )
    }

}