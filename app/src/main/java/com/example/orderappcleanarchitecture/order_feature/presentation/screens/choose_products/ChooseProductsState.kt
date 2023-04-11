package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products

import com.example.orderappcleanarchitecture.order_feature.presentation.model.ProductListItem

data class ChooseProductsState(
    val productsToShow: List<ProductListItem> = emptyList(),
    val selectedProducts: List<ProductListItem> = emptyList(),
    val productSearchQuery: String = "",
    val isConfirmOrderDialogShown: Boolean = false,
    val arePlusAndMinusEnabled: Boolean = true
)
