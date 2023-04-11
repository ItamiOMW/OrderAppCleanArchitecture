package com.example.orderappcleanarchitecture.order_feature.domain.repository

import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor
import com.example.orderappcleanarchitecture.order_feature.domain.model.Order

interface OrderRepository {

    suspend fun insertOrder(order: Order)

    suspend fun getOrders(): List<Order>

    suspend fun getVendors(): List<Vendor>

    suspend fun getProductsByVendorId(vendorId: Long): List<Product>

    suspend fun getVendorNameById(vendorId: Long): String

}