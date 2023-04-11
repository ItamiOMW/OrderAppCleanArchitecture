package com.example.orderappcleanarchitecture.order_feature.presentation.model

data class OrderDetailListItem(
    val orderId: Long,
    val vendorName: String,
    val orderDate: String,
    val products: List<ProductListItem>
)
