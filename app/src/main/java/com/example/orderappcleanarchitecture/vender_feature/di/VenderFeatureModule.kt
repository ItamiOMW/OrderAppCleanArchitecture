package com.example.orderappcleanarchitecture.vender_feature.di

import com.example.orderappcleanarchitecture.vender_feature.data.repository.VendorRepositoryImpl
import com.example.orderappcleanarchitecture.vender_feature.domain.repository.VenderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VenderFeatureModule {

    @Provides
    @Singleton
    fun provideVenderRepository(
        venderRepositoryImpl: VendorRepositoryImpl
    ): VenderRepository = venderRepositoryImpl

}