package com.example.orderappcleanarchitecture.order_feature.data.repository

import com.example.orderappcleanarchitecture.core.data.local.dao.OrderDao
import com.example.orderappcleanarchitecture.core.data.local.dao.ProductDao
import com.example.orderappcleanarchitecture.core.data.local.dao.VendorDao
import com.example.orderappcleanarchitecture.core.data.mapper.toOrder
import com.example.orderappcleanarchitecture.core.data.mapper.toOrderEntity
import com.example.orderappcleanarchitecture.core.data.mapper.toProduct
import com.example.orderappcleanarchitecture.core.data.mapper.toVendor
import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor
import com.example.orderappcleanarchitecture.order_feature.data.mapper.*
import com.example.orderappcleanarchitecture.order_feature.domain.model.Order
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val vendorDao: VendorDao,
    private val productDao: ProductDao,
    private val orderDao: OrderDao,
) : OrderRepository {


    override suspend fun insertOrder(order: Order) {
        val entity = order.toOrderEntity()
        val id = orderDao.insertOrder(entity)
        val orderProductEntities = order.products.map { boughtProduct ->
            boughtProduct.toOrderProductEntity(id)
        }
        orderDao.insertOrderProducts(orderProductEntities)
    }


    override suspend fun getOrders(): List<Order> {
        return orderDao.getOrdersWithProducts().map { orderWithProductsDataObject ->
            orderWithProductsDataObject.toOrder()
        }
    }


    override suspend fun getVendors(): List<Vendor> {
        return vendorDao.getVendors().map { vendorWithProductsDataObject ->
            vendorWithProductsDataObject.toVendor()
        }
    }


    override suspend fun getProductsByVendorId(vendorId: Long): List<Product> {
        return productDao.getProductsByVendorId(vendorId).map { productEntity ->
            productEntity.toProduct()
        }
    }


    override suspend fun getVendorNameById(vendorId: Long): String {
        return vendorDao.getVendorNameById(vendorId)
    }

}