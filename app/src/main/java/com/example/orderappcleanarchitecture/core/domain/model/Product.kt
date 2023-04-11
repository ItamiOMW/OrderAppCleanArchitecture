package com.example.orderappcleanarchitecture.core.domain.model

import com.example.orderappcleanarchitecture.core.domain.FilterableAndSortableByName

data class Product(
    val productId: Long,
    override val name: String,
    val pricePerUnitDollars: Double,
): FilterableAndSortableByName
