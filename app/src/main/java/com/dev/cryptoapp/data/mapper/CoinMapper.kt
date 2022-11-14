package com.dev.cryptoapp.data.mapper

import com.dev.cryptoapp.data.remote.dto.CoinDto
import com.dev.cryptoapp.domain.model.Coin

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    isActive = isActive,
    name = name,
    rank = rank,
    symbol = symbol
)