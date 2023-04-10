package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orderappcleanarchitecture.core.util.Constants

@Entity
data class VendorEntity(
    @PrimaryKey(autoGenerate = true)
    val vendorId: Long = Constants.UNKNOWN_ID,
    val name: String,
)