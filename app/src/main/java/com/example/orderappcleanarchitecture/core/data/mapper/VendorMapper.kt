package com.example.orderappcleanarchitecture.core.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.VendorWithProductsDataObject
import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor


fun VendorWithProductsDataObject.toVendor(): Vendor = Vendor(
    vendorId = vendor.vendorId,
    name = vendor.name,
    products = products.map { productEntity ->
        Product(
            productId = productEntity.productId,
            name = productEntity.name,
            pricePerUnitDollars = productEntity.pricePerUnitDollars
        )
    }
)