package com.dev.cryptoapp.presentation.coin_detail

import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isCoinLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
    val isChartLoading: Boolean = false,
    val chartCloses: List<Double> = emptyList()
)
