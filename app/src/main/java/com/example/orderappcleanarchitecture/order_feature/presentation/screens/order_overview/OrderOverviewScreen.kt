package com.example.orderappcleanarchitecture.order_feature.presentation.screens.order_overview

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.order_feature.presentation.components.OrderDetailDialog
import com.example.orderappcleanarchitecture.order_feature.presentation.components.OrderUiListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderOverviewScreen(
    state: OrderOverviewState,
    onEvent: (OrderOverviewEvent) -> Unit,
    onNavigateToChooseVendor: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_order_overview))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToChooseVendor() }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.desc_add_order)
                )
            }
        }
    ) { paddingValues ->
        if (state.orders.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.text_no_orders))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    state.orders,
                    key = { it.orderId }
                ) { orderListItem ->
                    OrderUiListItem(
                        orderListItem = orderListItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                onEvent(OrderOverviewEvent.OnOrderClick(orderListItem))
                            }
                            .padding(15.dp)
                    )
                }
            }
        }
        if (state.isOrderDialogShown && state.clickedOrderItem != null) {
            OrderDetailDialog(
                orderDetailListItem = state.clickedOrderItem,
                onDismiss = { onEvent(OrderOverviewEvent.OnDismissOrderDialog) }
            )
        }
    }

}