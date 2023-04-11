package com.example.orderappcleanarchitecture.core.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderWithProductsDataObject
import com.example.orderappcleanarchitecture.order_feature.domain.model.BoughtProduct
import com.example.orderappcleanarchitecture.order_feature.domain.model.Order

fun Order.toOrderEntity(): OrderEntity = OrderEntity(
    orderId = orderId,
    date = date,
    vendorTime = vendorTime,
    vendorId = vendorId,
    vendorName = vendorName
)

fun OrderWithProductsDataObject.toOrder(): Order = Order(
    orderId = order.orderId,
    date = order.date,
    vendorName = order.vendorName,
    vendorTime = order.vendorTime,
    vendorId = order.vendorId,
    products = products.zip(orderProducts).map { pair ->
        BoughtProduct(
            productId = pair.first.productId,
            name = pair.first.name,
            pricePerUnitDollars = pair.first.pricePerUnitDollars,
            amount = pair.second.amount
        )
    }
)