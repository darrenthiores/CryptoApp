package com.dev.cryptoapp.domain.use_case

import com.dev.cryptoapp.util.Resource
import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.repository.CoinRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> =
        repository.getCoins()
}