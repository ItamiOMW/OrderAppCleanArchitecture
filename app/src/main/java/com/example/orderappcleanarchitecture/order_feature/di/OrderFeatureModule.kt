package com.example.orderappcleanarchitecture.order_feature.di

import com.example.orderappcleanarchitecture.order_feature.data.repository.OrderRepositoryImpl
import com.example.orderappcleanarchitecture.order_feature.domain.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderFeatureModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ): OrderRepository = orderRepositoryImpl

}