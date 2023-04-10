package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Embedded
import androidx.room.Relation

data class VendorWithProductsDataObject(
    @Embedded val vendor: VendorEntity,
    @Relation(
        parentColumn = "vendorId",
        entityColumn = "belongsToVendor"
    )
    val products: List<ProductEntity>
)
