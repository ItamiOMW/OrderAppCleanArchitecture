package com.example.orderappcleanarchitecture.core.presentation.navigation

sealed class Screen(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    object OrderOverviewScreen : Screen(route = ORDER_OVERVIEW_SCREEN_ROUTE)

    object ChooseVendorScreen : Screen(route = CHOOSE_VENDOR_SCREEN_ROUTE)

    object ChooseProductsScreen : Screen(
        route = CHOOSE_PRODUCTS_SCREEN_ROUTE,
        VENDOR_ID_ARG
    ) {
        fun getRouteWithArgs(vendorId: Long): String = route.appendParams(
            VENDOR_ID_ARG to vendorId
        )
    }


    companion object {

        //Screen routes
        private const val ORDER_OVERVIEW_SCREEN_ROUTE = "order_overview"
        private const val CHOOSE_VENDOR_SCREEN_ROUTE = "choose_vendor"
        private const val CHOOSE_PRODUCTS_SCREEN_ROUTE = "choose_products"

        //Arguments
        const val VENDOR_ID_ARG = "vendor_id_arg"

    }


    internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
        val builder = StringBuilder(this)

        params.forEach {
            it.second?.toString()?.let { arg ->
                builder.append("/$arg")
            }
        }

        return builder.toString()
    }
}
