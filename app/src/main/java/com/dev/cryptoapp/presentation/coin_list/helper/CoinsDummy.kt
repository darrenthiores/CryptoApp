package com.dev.cryptoapp.presentation.coin_list.helper

import com.dev.cryptoapp.domain.model.Coin

object CoinsDummy {
    fun generateCoins(): List<Coin> = (0..20).map { 
        Coin(
            id = it.toString(),
            isActive = false,
            name = "PlaceHolder Name",
            rank = it,
            symbol = "PHS"
        )
    }
}