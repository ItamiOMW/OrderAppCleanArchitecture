package com.example.orderappcleanarchitecture.core.domain.usecase

import com.example.orderappcleanarchitecture.core.domain.FilterableAndSortableByName
import javax.inject.Inject

class FilterListByNameUseCase @Inject constructor() {

    operator fun <T> invoke(
        list: List<T>,
        name: String,
    ): List<T> where T : FilterableAndSortableByName {
        return list.filter { item -> item.name.lowercase().contains(name.lowercase()) }
    }

}