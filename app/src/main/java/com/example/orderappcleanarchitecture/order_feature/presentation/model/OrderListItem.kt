package com.example.orderappcleanarchitecture.order_feature.presentation.model

data class OrderListItem(
    val orderId: Long,
    val vendorName: String,
    val totalAmount: Double,
    val orderDate: String
)