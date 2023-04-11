package com.example.orderappcleanarchitecture.order_feature.domain.model

import com.example.orderappcleanarchitecture.core.util.Constants

data class Order(
    val orderId: Long = Constants.UNKNOWN_ID,
    val date: String,
    val vendorTime: String,
    val vendorId: Long,
    val vendorName: String,
    val products: List<BoughtProduct>
)
