package com.example.orderappcleanarchitecture.core.data.local.dao

import androidx.room.*
import com.example.orderappcleanarchitecture.core.data.local.entitiy.VendorEntity
import com.example.orderappcleanarchitecture.core.data.local.entitiy.VendorWithProductsDataObject

@Dao
interface VendorDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendorEntity: VendorEntity): Long //Returns id of inserted vender


    @Transaction
    @Query("SELECT * FROM VendorEntity")
    suspend fun getVenders(): List<VendorWithProductsDataObject>


    @Query("SELECT * FROM VendorEntity WHERE vendorId = :vendorId")
    suspend fun getVendorById(vendorId: Long): VendorWithProductsDataObject


    @Query("SELECT name FROM VendorEntity WHERE vendorId = :vendorId")
    suspend fun getVendorNameById(vendorId: Long): String


    @Query("DELETE FROM VendorEntity WHERE vendorId = :vendorId")
    suspend fun removeVendor(vendorId: Long)


}