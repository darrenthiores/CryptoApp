package com.dev.cryptoapp.data.remote.service

import com.dev.cryptoapp.data.remote.dto.CoinDetailDto
import com.dev.cryptoapp.data.remote.dto.CoinDto
import com.dev.cryptoapp.data.remote.dto.CoinOHLCVDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinPaprikaApi {
    @GET(GET_COINS_ROUTES)
    suspend fun getCoins(): List<CoinDto>

    @GET(GET_COIN_ROUTES)
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto

    @GET(GET_COIN_OHLCV)
    suspend fun getCoinOHLCV(
        @Path("coinId") coinId: String,
        @Query("start") start: Long,
        @Query("end") end: Long
    ): List<CoinOHLCVDto>

    companion object {
        const val BASE_URL = "https://api.coinpaprika.com"
        private const val GET_COINS_ROUTES = "/v1/coins"
        private const val GET_COIN_ROUTES = "/v1/coins/{coinId}"
        private const val GET_COIN_OHLCV = "/v1/coins/{coinId}/ohlcv/historical"
    }
}