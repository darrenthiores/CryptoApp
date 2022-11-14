package com.dev.cryptoapp.data.mapper

import com.dev.cryptoapp.data.remote.dto.CoinOHLCVDto
import com.dev.cryptoapp.domain.model.CoinOHCLV

fun CoinOHLCVDto.toCoinOHCLV(): CoinOHCLV =
    CoinOHCLV(
        close = close
    )