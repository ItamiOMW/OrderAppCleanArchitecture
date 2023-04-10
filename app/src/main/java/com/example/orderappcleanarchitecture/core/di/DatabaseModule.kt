package com.example.orderappcleanarchitecture.core.di

import android.content.Context
import androidx.room.Room
import com.example.orderappcleanarchitecture.core.data.local.OrderDatabase
import com.example.orderappcleanarchitecture.core.data.local.dao.OrderDao
import com.example.orderappcleanarchitecture.core.data.local.dao.ProductDao
import com.example.orderappcleanarchitecture.core.data.local.dao.VendorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOrderDatabase(
        @ApplicationContext context: Context,
    ): OrderDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = OrderDatabase::class.java,
            name = OrderDatabase.DB_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideOrderDao(
        orderDatabase: OrderDatabase
    ): OrderDao {
        return orderDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun provideVendorDao(
        orderDatabase: OrderDatabase
    ): VendorDao {
        return orderDatabase.vendorDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(
        orderDatabase: OrderDatabase
    ): ProductDao {
        return orderDatabase.productDao()
    }

}