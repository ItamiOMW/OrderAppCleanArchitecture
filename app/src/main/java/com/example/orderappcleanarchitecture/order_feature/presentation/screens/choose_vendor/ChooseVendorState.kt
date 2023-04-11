package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor

import com.example.orderappcleanarchitecture.order_feature.presentation.model.VendorListItem

data class ChooseVendorState(
    val vendorsToShow: List<VendorListItem> = emptyList(),
    val vendorSearchQuery: String = "",
)
