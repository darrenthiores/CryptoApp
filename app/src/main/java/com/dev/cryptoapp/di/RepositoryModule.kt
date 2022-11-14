package com.dev.cryptoapp.di

import com.dev.cryptoapp.data.repository.CoinRepositoryImpl
import com.dev.cryptoapp.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(
        repositoryImpl: CoinRepositoryImpl
    ): CoinRepository
}