package com.example.orderappcleanarchitecture.core.domain.model

data class Product(
    val productId: Long,
    val name: String,
    val pricePerUnitDollars: Double,
    val vendorId: Long,
    val vendorName: String
)
