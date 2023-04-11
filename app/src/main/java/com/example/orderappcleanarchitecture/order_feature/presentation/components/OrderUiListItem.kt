package com.example.orderappcleanarchitecture.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderListItem


@Composable
fun OrderUiListItem(
    orderListItem: OrderListItem,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = orderListItem.vendorName,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${orderListItem.totalAmount} $",
                fontWeight = FontWeight.Bold
            )
        }
        Divider()
        Text(
            text = orderListItem.orderDate,
            textAlign = TextAlign.Center,
        )
    }

}