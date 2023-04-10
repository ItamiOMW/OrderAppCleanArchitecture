package com.example.orderappcleanarchitecture.core.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orderappcleanarchitecture.core.util.Constants

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val orderId: Long = Constants.UNKNOWN_ID,
    val date: String,
    val vendorTime: String,
    val vendorId: Long,
    val vendorName: String
)
