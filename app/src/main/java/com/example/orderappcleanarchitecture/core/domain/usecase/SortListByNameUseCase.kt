package com.example.orderappcleanarchitecture.core.domain.usecase

import com.example.orderappcleanarchitecture.core.domain.FilterableAndSortableByName
import javax.inject.Inject

class SortListByNameUseCase @Inject constructor() {

    operator fun <T> invoke(
        list: List<T>,
        isAscending: Boolean = true
    ): List<T> where T : FilterableAndSortableByName {
        return if (isAscending) {
            list.sortedBy { item -> item.name }
        } else {
            list.sortedByDescending { item -> item.name }
        }
    }

}