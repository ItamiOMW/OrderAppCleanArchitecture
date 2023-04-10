package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Entity

@Entity(primaryKeys = ["orderId", "productId"])
data class OrderProductEntity(
    val orderId: Long,
    val productId: Long,
    val amount: Int,
)
