package com.dev.cryptoapp.presentation.coin_detail.helper

import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.model.TeamMember

object CoinDummy {
    fun generateTeamMembers(): List<TeamMember> = (0..3).map {
        TeamMember(
            id = it.toString(),
            name = "PlaceHolder Name",
            position = "PH Position"
        )
    }

    fun generateTags(): List<String> = (0..5).map {
        "PlaceHolderTag-$it"
    }
}