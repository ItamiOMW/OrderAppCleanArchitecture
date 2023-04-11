package com.example.orderappcleanarchitecture.order_feature.presentation.screens.choose_vendor

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orderappcleanarchitecture.R
import com.example.orderappcleanarchitecture.order_feature.presentation.components.VendorUiListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseVendorScreen(
    state: ChooseVendorState,
    onEvent: (ChooseVendorEvent) -> Unit,
    onNavigateToChooseProducts: (vendorId: Long) -> Unit,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_vendor_selection))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.desc_navigate_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                value = state.vendorSearchQuery,
                onValueChange = { query -> onEvent(ChooseVendorEvent.OnVendorSearchQueryChange(query)) },
                maxLines = 1,
                label = { Text(text = stringResource(R.string.label_search_vendor)) }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.vendorsToShow, key = { it.vendorId }) { vendorListItem ->
                    VendorUiListItem(
                        vendorListItem = vendorListItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                onNavigateToChooseProducts(vendorListItem.vendorId)
                            }
                            .padding(15.dp)
                    )
                }
            }
        }
    }

}