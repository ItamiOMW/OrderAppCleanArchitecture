package com.example.orderappcleanarchitecture.order_feature.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.orderappcleanarchitecture.order_feature.presentation.model.ProductListItem


@Composable
fun ConfirmOrderDialog(
    selectedProducts: List<ProductListItem>,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        text = "Confirm",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(modifier = Modifier.padding(10.dp))
                    if (selectedProducts.isNotEmpty()) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            items(
                                selectedProducts,
                                key = { it.productId }
                            ) { item: ProductListItem ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${item.selectedAmount} x ${item.productName}"
                                    )
                                    Text(
                                        text = "%.2f".format(item.pricePerUnitDollars * item.selectedAmount)
                                    )
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Select items to order"
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Sum",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "%.2f".format(selectedProducts.sumOf { it.selectedAmount * it.pricePerUnitDollars }) + " $",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                onDismiss()
                            },
                        ) {
                            Text(
                                text = "Cancel",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        Button(
                            onClick = {
                                onConfirm()
                            },
                        ) {
                            Text(
                                text = "Confirm",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}