package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orderappcleanarchitecture.core.util.Constants

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Long = Constants.UNKNOWN_ID,
    val name: String,
    val pricePerUnitDollars: Double,
    val belongsToVendor: Long
)
