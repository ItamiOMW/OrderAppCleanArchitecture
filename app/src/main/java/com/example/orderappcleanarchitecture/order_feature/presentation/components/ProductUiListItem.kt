package com.example.orderappcleanarchitecture.order_feature.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.order_feature.presentation.model.ProductListItem


@Composable
fun ProductUiListItem(
    productListItem: ProductListItem,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = productListItem.productName,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "%.2f".format(productListItem.pricePerUnitDollars) + " $",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            AnimatedVisibility(visible = productListItem.selectedAmount > 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.desc_icon_checkmark)
                    )
                    Text(
                        text = "${productListItem.selectedAmount} x",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        AnimatedVisibility(visible = productListItem.isExpanded) {
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                IconButton(
                    onClick = { onMinusClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Minus"
                    )
                }
                IconButton(
                    onClick = { onPlusClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Plus"
                    )
                }
            }
        }
    }

}