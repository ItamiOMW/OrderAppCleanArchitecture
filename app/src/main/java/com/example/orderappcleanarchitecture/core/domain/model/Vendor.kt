package com.example.orderappcleanarchitecture.core.domain.model

data class Vendor(
    val vendorId: Long,
    val name: String,
    val products: List<Product>
)
