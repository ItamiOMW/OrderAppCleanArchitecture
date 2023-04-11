package com.example.orderappcleanarchitecture.order_feature.presentation.model

data class ProductListItem(
    val productId: Long,
    val productName: String,
    val pricePerUnitDollars: Double,
    val selectedAmount: Int,
    val isExpanded: Boolean
)
