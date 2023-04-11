package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products

sealed class ChooseProductsEvent {

    data class OnListItemClick(val productId: Long): ChooseProductsEvent()

    data class OnItemPlusClick(val productId: Long): ChooseProductsEvent()

    data class OnItemMinusClick(val productId: Long): ChooseProductsEvent()

    data class OnSearchProductQueryChange(val query: String): ChooseProductsEvent()

    object OnConfirmOrder: ChooseProductsEvent()

    object OnShowConfirmOrderDialog : ChooseProductsEvent()

    object OnDismissConfirmOrderDialog : ChooseProductsEvent()

}