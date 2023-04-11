package com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderappcleanarchitecture.core.util.Constants
import com.example.orderappcleanarchitecture.order_feature.domain.model.Order
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import com.example.orderappcleanarchitecture.order_feature.presentation.mapper.toOrderDetailListItem
import com.example.orderappcleanarchitecture.order_feature.presentation.mapper.toOrderListItem
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderListItem
import com.example.orderappcleanarchitecture.vendor_feature.domain.repository.VendorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class OrderOverviewViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val vendorRepository: VendorRepository
) : ViewModel() {

    private lateinit var orders: List<Order>

    var state by mutableStateOf(OrderOverviewState())
        private set

    init {
        viewModelScope.launch {
            orders = orderRepository.getOrders()
            setupOrderList()
        }
    }


    fun onEvent(event: OrderOverviewEvent) {
        when (event) {
            is OrderOverviewEvent.OnOrderClick -> {
                onOrderItemClick(event.orderItem)
            }
            is OrderOverviewEvent.OnDismissOrderDialog -> {
                onDismissOrderDialog()
            }
        }
    }

    private fun onOrderItemClick(orderListItem: OrderListItem) {
        val clickedOrder = orders.firstOrNull {
            it.orderId == orderListItem.orderId
        }?.toOrderDetailListItem()
        state = state.copy(clickedOrderItem = clickedOrder, isOrderDialogShown = true)
    }

    private fun onDismissOrderDialog() {
        state = state.copy(isOrderDialogShown = false, clickedOrderItem = null)
    }

    private fun setupOrderList() {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER_PATTERN)
        state = state.copy(orders = orders.map { order ->
            order.toOrderListItem()
        }.sortedByDescending { LocalDateTime.parse(it.orderDate, formatter) })
    }

}