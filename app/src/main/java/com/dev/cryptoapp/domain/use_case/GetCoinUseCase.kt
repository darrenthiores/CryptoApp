package com.dev.cryptoapp.domain.use_case

import com.dev.cryptoapp.util.Resource
import com.dev.cryptoapp.domain.model.CoinDetail
import com.dev.cryptoapp.domain.repository.CoinRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(
        coinId: String
    ): Resource<CoinDetail> = repository.getCoinById(coinId)
}