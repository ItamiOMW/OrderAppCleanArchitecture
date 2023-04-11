package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderappcleanarchitecture.core.domain.model.Vendor
import com.example.orderappcleanarchitecture.core.domain.usecase.FilterListByNameUseCase
import com.example.orderappcleanarchitecture.core.domain.usecase.SortListByNameUseCase
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import com.example.orderappcleanarchitecture.order_feature.presentation.mapper.toVendorListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseVendorViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val filterListByNameUseCase: FilterListByNameUseCase,
    private val sortListByNameUseCase: SortListByNameUseCase,
) : ViewModel() {

    private lateinit var vendors: List<Vendor>

    var state by mutableStateOf(ChooseVendorState())
        private set

    init {
        viewModelScope.launch {
            vendors = orderRepository.getVendors()
            setupVendorsToShow()
        }
    }

    fun onEvent(event: ChooseVendorEvent) {
        when (event) {
            is ChooseVendorEvent.OnVendorSearchQueryChange -> {
                onVendorSearchQueryChange(event.query)
            }
        }
    }

    private fun onVendorSearchQueryChange(
        query: String
    ) {
        state = state.copy(vendorSearchQuery = query)
        setupVendorsToShow()
    }

    private fun setupVendorsToShow() {
        val vendorsToShow = filterListByNameUseCase(
            list = sortListByNameUseCase(vendors),
            state.vendorSearchQuery
        ).map { vendor ->
            vendor.toVendorListItem()
        }
        state = state.copy(vendorsToShow = vendorsToShow)
    }

}