package com.example.orderappcleanarchitecture.vendor_feature.data.repository

import com.example.orderappcleanarchitecture.core.data.local.dao.ProductDao
import com.example.orderappcleanarchitecture.core.data.local.dao.VendorDao
import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor
import com.example.orderappcleanarchitecture.vendor_feature.data.mapper.toProductEntity
import com.example.orderappcleanarchitecture.vendor_feature.data.mapper.toVendorEntity
import com.example.orderappcleanarchitecture.vendor_feature.domain.repository.VendorRepository
import javax.inject.Inject

class VendorRepositoryImpl @Inject constructor(
    private val vendorDao: VendorDao,
    private val productDao: ProductDao
): VendorRepository {

    override suspend fun insertVendors(list: List<Vendor>) {
        list.forEach { vendor ->
            val entity = vendor.toVendorEntity()
            val id = vendorDao.insertVendor(entity)
            insertProducts(vendor.products, id)
        }
    }

    override suspend fun insertProducts(list: List<Product>, venderId: Long) {
        val entities = list.map {  product ->
            product.toProductEntity(venderId)
        }
        productDao.insertProducts(entities)
    }

}