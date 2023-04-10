package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithProductsDataObject(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "productId",
        associateBy = Junction(OrderProductEntity::class)
    )
    val products: List<ProductEntity>,
    @Relation( //Trick to get amount
        parentColumn = "orderId",
        entityColumn = "orderId",
    )
    val orderProducts: List<OrderProductEntity>,
)
