package com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview

import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderDetailListItem
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderListItem

data class OrderOverviewState(
    val orders: List<OrderListItem> = emptyList(),
    val isOrderDialogShown: Boolean = false,
    val clickedOrderItem: OrderDetailListItem? = null
)
