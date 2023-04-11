package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.usecase.FilterListByNameUseCase
import com.example.orderappcleanarchitecture.core.domain.usecase.SortListByNameUseCase
import com.example.orderappcleanarchitecture.core.presentation.navigation.Screen
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import com.example.orderappcleanarchitecture.order_feature.domain.usecase.ConfirmOrderUseCase
import com.example.orderappcleanarchitecture.order_feature.presentation.mapper.toBoughtProduct
import com.example.orderappcleanarchitecture.order_feature.presentation.mapper.toProductListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseProductsViewModel @Inject constructor(
    private val application: Application,
    private val orderRepository: OrderRepository,
    private val sortListByNameUseCase: SortListByNameUseCase,
    private val filterListByNameUseCase: FilterListByNameUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private lateinit var products: List<Product>

    private var vendorId: Long? = null


    var state by mutableStateOf(ChooseProductsState())
        private set

    private val _uiEvent = MutableSharedFlow<ChooseProductsUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<Long>(Screen.VENDOR_ID_ARG)?.let { vendorId ->
                this@ChooseProductsViewModel.vendorId = vendorId
                products = orderRepository.getProductsByVendorId(vendorId)
                setupProductsToShow()
            }
        }
    }

    fun onEvent(event: ChooseProductsEvent) {
        when (event) {
            is ChooseProductsEvent.OnListItemClick -> {
                onListItemClick(event.productId)
            }
            is ChooseProductsEvent.OnItemPlusClick -> {
                onItemPlusClick(event.productId)
            }
            is ChooseProductsEvent.OnItemMinusClick -> {
                onItemMinusClick(event.productId)
            }
            is ChooseProductsEvent.OnSearchProductQueryChange -> {
                onSearchProductQueryChange(query = event.query)
            }
            is ChooseProductsEvent.OnConfirmOrder -> {
                onConfirm()
            }
            is ChooseProductsEvent.OnShowConfirmOrderDialog -> {
                onShowConfirmOrderDialog()
            }
            is ChooseProductsEvent.OnDismissConfirmOrderDialog -> {
                onDismissConfirmOrderDialog()
            }
        }
    }

    private fun onConfirm() {
        viewModelScope.launch {
            val id = vendorId ?: return@launch
            confirmOrderUseCase(
                products = state.selectedProducts.map { it.toBoughtProduct() },
                vendorId = id
            )
            state = state.copy(isConfirmOrderDialogShown = false)
            _uiEvent.emit(ChooseProductsUiEvent.ShowSnackbar(application.getString(R.string.text_order_confirmed)))
            _uiEvent.emit(ChooseProductsUiEvent.OrderConfirmed)
        }
    }

    private fun onListItemClick(productId: Long) {
        val index = getProductIndex(productId = productId)
        if (index < 0) return
        val updatedItem = state.productsToShow[index].copy(
            isExpanded = !state.productsToShow[index].isExpanded
        )
        val updatedList = state.productsToShow.toMutableList().apply {
            removeAt(index)
            add(index, updatedItem)
        }
        state = state.copy(productsToShow = updatedList)
    }

    private fun onItemPlusClick(productId: Long) {

        state = state.copy(arePlusAndMinusEnabled = false)
        val index = getProductIndex(productId = productId)

        if (index < 0) {
            state = state.copy(arePlusAndMinusEnabled = true)
            return
        }

        val currentSelectedAmount = state.productsToShow[index].selectedAmount

        state.productsToShow.toMutableList().apply {
            this[index] = this[index].copy(selectedAmount = currentSelectedAmount + 1)
            state = state.copy(productsToShow = this)
        }

        var newSelectedProducts = state.selectedProducts.toMutableList()

        if (currentSelectedAmount == 0) {
            newSelectedProducts.add(state.productsToShow[index])
        } else {
            newSelectedProducts = newSelectedProducts.map { productListItem ->
                if (productListItem.productId == productId) {
                    productListItem.copy(selectedAmount = currentSelectedAmount + 1)
                } else {
                    productListItem
                }
            }.toMutableList()
        }
        state = state.copy(selectedProducts = newSelectedProducts, arePlusAndMinusEnabled = true)
    }


    private fun onItemMinusClick(productId: Long) {
        state = state.copy(arePlusAndMinusEnabled = false)
        val index = getProductIndex(productId = productId)

        if (index < 0) {
            state = state.copy(arePlusAndMinusEnabled = true)
            return
        }

        val currentSelectedAmount = state.productsToShow[index].selectedAmount

        if (currentSelectedAmount == 0) {
            state = state.copy(arePlusAndMinusEnabled = true)
            return
        }

        if (currentSelectedAmount == 1) {
            val newSelectedProduct = state.selectedProducts.toMutableList().apply {
                removeAll { it.productId == state.productsToShow[index].productId }
            }
            state = state.copy(selectedProducts = newSelectedProduct)
        }
        val newProductsToShow = state.productsToShow.toMutableList().apply {
            this[index] = this[index].copy(selectedAmount = currentSelectedAmount - 1)
        }
        state = state.copy(productsToShow = newProductsToShow, arePlusAndMinusEnabled = true)
    }


    private fun onSearchProductQueryChange(query: String) {
        state = state.copy(productSearchQuery = query)
        setupProductsToShow()
    }


    private fun setupProductsToShow() {
        val productsToShow = filterListByNameUseCase(
            list = sortListByNameUseCase(products),
            name = state.productSearchQuery
        ).map { product ->
            product.toProductListItem()
        }.map { productListItem ->
            val selectedItem = state.selectedProducts.firstOrNull {
                it.productId == productListItem.productId
            }
            if (selectedItem != null) {
                productListItem.copy(selectedAmount = selectedItem.selectedAmount)
            } else {
                productListItem
            }
        }
        state = state.copy(productsToShow = productsToShow)
    }

    private fun onDismissConfirmOrderDialog() {
        state = state.copy(isConfirmOrderDialogShown = false)
    }

    private fun onShowConfirmOrderDialog() {
        state = state.copy(isConfirmOrderDialogShown = true)
    }

    private fun getProductIndex(productId: Long): Int {
        return state.productsToShow.indexOfFirst { it.productId == productId }
    }

}