package com.example.orderappcleanarchitecture.vendor_feature.data.mapper

import com.example.orderappcleanarchitecture.core.data.local.entitiy.VendorEntity
import com.example.orderappcleanarchitecture.core.domain.model.Vendor


fun Vendor.toVendorEntity(): VendorEntity = VendorEntity(
    vendorId = vendorId,
    name = name
)