package com.example.orderappcleanarchitecture.order_feature.presentation.mapper

import com.example.orderappcleanarchitecture.order_feature.domain.model.Order
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderDetailListItem
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderListItem


fun Order.toOrderDetailListItem(): OrderDetailListItem = OrderDetailListItem(
    orderId = orderId,
    vendorName = vendorName,
    orderDate = date,
    products = products.map { boughtProduct -> boughtProduct.toProductListItem() }
)

fun Order.toOrderListItem(): OrderListItem = OrderListItem(
    orderId = orderId,
    vendorName = vendorName,
    totalAmount = products.sumOf { it.amount * it.pricePerUnitDollars },
    orderDate = date
)