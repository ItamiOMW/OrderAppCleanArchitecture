package com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview

import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderListItem

sealed class OrderOverviewEvent {

    data class OnOrderClick(val orderItem: OrderListItem) : OrderOverviewEvent()

    object OnDismissOrderDialog : OrderOverviewEvent()

}