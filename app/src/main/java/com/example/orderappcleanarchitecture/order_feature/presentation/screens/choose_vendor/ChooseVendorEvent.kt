package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor

sealed class ChooseVendorEvent {

    data class OnVendorSearchQueryChange(val query: String) : ChooseVendorEvent()

}