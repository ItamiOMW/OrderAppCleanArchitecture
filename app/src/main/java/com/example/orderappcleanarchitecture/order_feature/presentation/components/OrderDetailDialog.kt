package com.example.orderappcleanarchitecture.order_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.order_feature.presentation.model.OrderDetailListItem


@Composable
fun OrderDetailDialog(
    orderDetailListItem: OrderDetailListItem,
    onDismiss: () -> Unit,
) {

    val totalAmount = remember(orderDetailListItem.products) {
        orderDetailListItem.products.sumOf { it.selectedAmount * it.pricePerUnitDollars }
    }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(
                            R.string.text_ordered_from,
                            orderDetailListItem.vendorName
                        ),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = orderDetailListItem.orderDate,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                    Divider(modifier = Modifier.padding(top = 10.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier.padding(15.dp)
                    ) {
                        items(
                            orderDetailListItem.products,
                            key = { it.productId }
                        ) { productListItem ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${productListItem.selectedAmount}x ${productListItem.productName}"
                                )
                                val totalAmountFormatted = remember(
                                    productListItem.selectedAmount,
                                    productListItem.pricePerUnitDollars
                                ) {
                                    "%.2f".format(productListItem.selectedAmount * productListItem.pricePerUnitDollars) + " $"
                                }
                                Text(
                                    text = totalAmountFormatted
                                )
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.text_total_sum),
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "$totalAmount $",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }

}