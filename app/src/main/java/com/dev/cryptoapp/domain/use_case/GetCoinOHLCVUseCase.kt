package com.dev.cryptoapp.domain.use_case

import com.dev.cryptoapp.domain.model.CoinOHCLV
import com.dev.cryptoapp.domain.repository.CoinRepository
import com.dev.cryptoapp.util.Resource
import com.dev.cryptoapp.util.dispatchers.DispatchersProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetCoinOHLCVUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val dispatchers: DispatchersProvider
) {
    suspend operator fun invoke(
        coinId: String
    ): List<Resource<CoinOHCLV>> =
        withContext(
            dispatchers.io
        ) {
            val currentTimeInSeconds = System.currentTimeMillis()/1000
            val timeMinDay = currentTimeInSeconds - 86400
            val closes = mutableListOf<Deferred<Resource<CoinOHCLV>>>()
//            val closes = mutableListOf<Resource<CoinOHCLV>>()

            for(i in 1..24) {
                val timeStart = timeMinDay + i*3600
                val timeEnd = timeStart + 3600
                val response = async {
                    repository.getCoinOHLCV(
                        coinId = coinId,
                        start = timeStart,
                        end = timeEnd
                    )
                }

                closes.add(response)
//                closes.add(response.await())
            }

            closes.awaitAll()
//            closes
        }
}