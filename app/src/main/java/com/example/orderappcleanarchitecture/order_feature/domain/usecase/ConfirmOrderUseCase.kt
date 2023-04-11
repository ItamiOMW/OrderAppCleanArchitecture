package com.example.orderappcleanarchitecture.order_feature.domain.usecase

import com.example.orderappcleanarchitecture.core.util.Constants
import com.example.orderappcleanarchitecture.order_feature.domain.model.BoughtProduct
import com.example.orderappcleanarchitecture.order_feature.domain.model.Order
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ConfirmOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(products: List<BoughtProduct>, vendorId: Long) {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER_PATTERN)
        val date = formatter.format(LocalDateTime.now())
        val vendorName = orderRepository.getVendorNameById(vendorId = vendorId)
        orderRepository.insertOrder(
            Order(
                date = date,
                vendorName = vendorName,
                vendorId = vendorId,
                vendorTime = "As Fast as Possible",
                products = products
            )
        )
    }

}