package com.example.orderappcleanarchitecture.core.domain.model

import com.example.orderappcleanarchitecture.core.domain.FilterableAndSortableByName

data class Vendor(
    val vendorId: Long,
    override val name: String,
    val products: List<Product>
): FilterableAndSortableByName
