package com.example.orderappcleanarchitecture.order_feature.presentation.mapper

import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.order_feature.domain.model.BoughtProduct
import com.example.orderappcleanarchitecture.order_feature.presentation.model.ProductListItem


fun BoughtProduct.toProductListItem(): ProductListItem = ProductListItem(
    productId = productId,
    productName = name,
    pricePerUnitDollars = pricePerUnitDollars,
    selectedAmount = amount,
    isExpanded = false
)

fun Product.toProductListItem(): ProductListItem = ProductListItem(
    productId = productId,
    productName = name,
    pricePerUnitDollars = pricePerUnitDollars,
    selectedAmount = 0,
    isExpanded = false
)

fun ProductListItem.toBoughtProduct(): BoughtProduct = BoughtProduct(
    productId = productId,
    name = productName,
    pricePerUnitDollars = pricePerUnitDollars,
    amount = selectedAmount
)