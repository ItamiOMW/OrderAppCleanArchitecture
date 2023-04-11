package com.example.orderappcleanarchitecture.order_feature.presentation.mapper

import com.example.orderappcleanarchitecture.core.domain.model.Vendor
import com.example.orderappcleanarchitecture.order_feature.presentation.model.VendorListItem


fun Vendor.toVendorListItem(): VendorListItem = VendorListItem(
    vendorId = vendorId,
    vendorName = name
)