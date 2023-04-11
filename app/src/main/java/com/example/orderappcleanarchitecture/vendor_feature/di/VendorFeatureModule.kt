package com.example.orderappcleanarchitecture.vendor_feature.di

import com.example.orderappcleanarchitecture.vendor_feature.data.repository.VendorRepositoryImpl
import com.example.orderappcleanarchitecture.vendor_feature.domain.repository.VendorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VendorFeatureModule {

    @Provides
    @Singleton
    fun provideVenderRepository(
        venderRepositoryImpl: VendorRepositoryImpl
    ): VendorRepository = venderRepositoryImpl

}