package com.example.orderappcleanarchitecture.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orderappcleanarchitecture.core.data.local.entitiy.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity): Long //Returns ID of inserted Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(list: List<ProductEntity>) //Returns ID of inserted Product


    @Query("SELECT * FROM ProductEntity WHERE belongsToVendor = :vendorId")
    suspend fun getProductsByVendorId(vendorId: Long): List<ProductEntity>

}