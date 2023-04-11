package com.example.orderappcleanarchitecture.order_feature.domain.model

data class BoughtProduct(
    val productId: Long,
    val name: String,
    val pricePerUnitDollars: Double,
    val amount: Int
)
