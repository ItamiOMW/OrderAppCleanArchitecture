package com.example.orderappcleanarchitecture.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.orderappcleanarchitecture.order_feature.presentation.model.VendorListItem


@Composable
fun VendorUiListItem(
    vendorListItem: VendorListItem,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = vendorListItem.vendorName)
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Icon Arrow Right"
        )
    }

}