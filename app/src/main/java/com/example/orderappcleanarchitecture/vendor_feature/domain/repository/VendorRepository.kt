package com.example.orderappcleanarchitecture.vendor_feature.domain.repository

import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor

interface VendorRepository {

    suspend fun insertVendors(list: List<Vendor>)

    suspend fun insertProducts(list: List<Product>, venderId: Long)

}