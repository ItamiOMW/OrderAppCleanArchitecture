package com.example.orderappcleanarchitecture.vender_feature.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.ProductEntity
import com.example.orderappcleanarchitecture.core.domain.model.Product


fun Product.toProductEntity(): ProductEntity = ProductEntity(
    productId = productId,
    name = name,
    pricePerUnitDollars = pricePerUnitDollars,
    belongsToVendor = vendorId
)