package com.dev.cryptoapp.data.remote.dto

import com.squareup.moshi.Json

data class CoinOHLCVDto(
    val close: Double,
    val high: Double,
    val low: Double,
    @field:Json(name = "market_cap")
    val marketCap: Long,
    val `open`: Double,
    @field:Json(name = "time_close")
    val timeClose: String,
    @field:Json(name = "time_open")
    val timeOpen: String,
    val volume: Long
)