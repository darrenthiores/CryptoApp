package com.dev.cryptoapp.domain.repository

import com.dev.cryptoapp.util.Resource
import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.model.CoinDetail
import com.dev.cryptoapp.domain.model.CoinOHCLV
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId: String): Resource<CoinDetail>

    suspend fun getCoinOHLCV(
        coinId: String,
        start: Long,
        end: Long
    ): Resource<CoinOHCLV>
}