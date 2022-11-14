package com.dev.cryptoapp.di

import com.dev.cryptoapp.util.dispatchers.DispatchersProvider
import com.dev.cryptoapp.util.dispatchers.StandardDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun providesDispatchers(): DispatchersProvider =
        StandardDispatchers()
}