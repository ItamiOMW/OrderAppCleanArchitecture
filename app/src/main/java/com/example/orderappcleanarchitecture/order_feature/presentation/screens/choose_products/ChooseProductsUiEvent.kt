package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products

sealed class ChooseProductsUiEvent {

    data class ShowSnackbar(val message: String): ChooseProductsUiEvent()

    object OrderConfirmed: ChooseProductsUiEvent()

}
