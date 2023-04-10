package com.example.orderappcleanarchitecture.core.data.local.dao

import androidx.room.*
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderProductEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.OrderWithProductsDataObject

@Dao
interface OrderDao {

    @Transaction
    @Query("SELECT * FROM OrderEntity")
    suspend fun getOrdersWithProducts(): List<OrderWithProductsDataObject>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity): Long //Returns ID of inserted Order


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderProducts(list: List<OrderProductEntity>)

}