package com.example.orderappcleanarchitecture.core.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    startGraphRoute: String = Graph.MAIN,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startGraphRoute,
        modifier = modifier
    ) {
        mainNavGraph(navController)
    }

}

object Graph {

    const val ROOT = "root_graph"

    const val MAIN = "main_graph"

}