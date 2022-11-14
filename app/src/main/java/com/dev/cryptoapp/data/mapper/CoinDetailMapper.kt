package com.dev.cryptoapp.data.mapper

import com.dev.cryptoapp.data.remote.dto.CoinDetailDto
import com.dev.cryptoapp.data.remote.dto.TeamMemberDto
import com.dev.cryptoapp.domain.model.CoinDetail
import com.dev.cryptoapp.domain.model.TeamMember

fun CoinDetailDto.toCoinDetail(): CoinDetail = CoinDetail(
    coinId = id,
    name = name,
    description = description,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
    tags = tags.map { it.name },
    team = team.map { it.toTeamMember() }
)

fun TeamMemberDto.toTeamMember(): TeamMember = TeamMember(
    id = id,
    name = name,
    position = position
)