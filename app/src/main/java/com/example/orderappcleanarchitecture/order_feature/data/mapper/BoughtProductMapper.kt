package com.example.orderappcleanarchitecture.order_feature.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderProductEntity
import com.example.orderappcleanarchitecture.order_feature.domain.model.BoughtProduct


fun BoughtProduct.toOrderProductEntity(orderId: Long): OrderProductEntity = OrderProductEntity(
    productId = productId,
    orderId = orderId,
    amount = amount
)