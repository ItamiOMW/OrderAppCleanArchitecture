package com.example.orderappcleanarchitecture.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orderappcleanarchitecture.core.data.local.dao.OrderDao
import com.example.orderappcleanarchitecture.core.data.local.dao.ProductDao
import com.example.orderappcleanarchitecture.core.data.local.dao.VendorDao
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderProductEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.ProductEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.VendorEntity

@Database(
    entities = [
        OrderEntity::class,
        OrderProductEntity::class,
        ProductEntity::class,
        VendorEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class OrderDatabase: RoomDatabase() {

    abstract fun orderDao(): OrderDao

    abstract fun productDao(): ProductDao

    abstract fun vendorDao(): VendorDao


    companion object {

        const val DB_NAME = "order.db"

    }
}