package com.example.orderappcleanarchitecture.core.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.orderappcleanarchitecture.core.presentation.navigation.Screen
import com.example.orderappcleanarchitecture.core.util.Constants
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products.ChooseProductScreen
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_products.ChooseProductsViewModel
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor.ChooseVendorScreen
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor.ChooseVendorViewModel
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview.OrderOverviewScreen
import com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview.OrderOverviewViewModel


fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        route = Graph.MAIN,
        startDestination = Screen.OrderOverviewScreen.fullRoute
    ) {
        composable(
            route = Screen.OrderOverviewScreen.fullRoute
        ) {
            val viewModel: OrderOverviewViewModel = hiltViewModel()
            OrderOverviewScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onNavigateToChooseVendor = { navController.navigate(Screen.ChooseVendorScreen.fullRoute) }
            )
        }
        composable(
            route = Screen.ChooseVendorScreen.fullRoute
        ) {
            val viewModel: ChooseVendorViewModel = hiltViewModel()
            ChooseVendorScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onNavigateToChooseProducts = { vendorId ->
                    navController.navigate(Screen.ChooseProductsScreen.getRouteWithArgs(vendorId))
                },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = Screen.ChooseProductsScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.VENDOR_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = Constants.UNKNOWN_ID
                }
            )
        ) {
            val viewModel: ChooseProductsViewModel = hiltViewModel()
            ChooseProductScreen(
                state = viewModel.state,
                uiEvent = viewModel.uiEvent,
                onEvent = viewModel::onEvent,
                onNavigateToStartScreen = { navController.navigate(navController.graph.startDestinationId) },
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}