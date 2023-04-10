package com.example.orderappcleanarchitecture.vender_feature.domain.repository

import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor

interface VenderRepository {

    suspend fun insertVendors(list: List<Vendor>)

    suspend fun insertProducts(list: List<Product>, venderId: Long)

}