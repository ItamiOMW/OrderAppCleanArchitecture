package com.example.orderappcleanarchitecture.core.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.ProductEntity
import com.example.orderappcleanarchitecture.core.domain.model.Product


fun ProductEntity.toProduct(): Product = Product(
    productId = productId,
    name = name,
    pricePerUnitDollars = pricePerUnitDollars
)