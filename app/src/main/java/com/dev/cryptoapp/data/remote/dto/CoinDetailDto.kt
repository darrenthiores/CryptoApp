package com.dev.cryptoapp.data.remote.dto
import com.squareup.moshi.Json


data class CoinDetailDto(
    val description: String,
    @field:Json(name = "development_status")
    val developmentStatus: String,
    @field:Json(name = "first_data_at")
    val firstDataAt: String,
    @field:Json(name = "hardware_wallet")
    val hardwareWallet: Boolean,
    @field:Json(name = "hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @field:Json(name = "is_active")
    val isActive: Boolean,
    @field:Json(name = "is_new")
    val isNew: Boolean,
    @field:Json(name = "last_data_at")
    val lastDataAt: String,
    val links: LinksDto,
    @field:Json(name = "links_extended")
    val linksExtended: List<LinksExtendedDto>,
    val logo: String,
    val message: String,
    val name: String,
    @field:Json(name = "open_source")
    val openSource: Boolean,
    @field:Json(name = "org_structure")
    val orgStructure: String,
    @field:Json(name = "proof_type")
    val proofType: String,
    val rank: Int,
    @field:Json(name = "started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<TagDto>,
    val team: List<TeamMemberDto>,
    val type: String,
    val whitepaper: WhitepaperDto
)

data class LinksDto(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @field:Json(name = "source_code")
    val sourceCode: List<String>,
    val website: List<String>,
    val youtube: List<String>
)

data class LinksExtendedDto(
    val stats: StatsDto,
    val type: String,
    val url: String
)

data class TagDto(
    @field:Json(name = "coin_counter")
    val coinCounter: Int,
    @field:Json(name = "ico_counter")
    val icoCounter: Int,
    val id: String,
    val name: String
)

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

data class WhitepaperDto(
    val link: String,
    val thumbnail: String
)

data class StatsDto(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)