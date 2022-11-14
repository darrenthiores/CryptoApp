package com.dev.cryptoapp.data.repository

import com.dev.cryptoapp.util.Resource
import com.dev.cryptoapp.util.dispatchers.DispatchersProvider
import com.dev.cryptoapp.data.mapper.toCoin
import com.dev.cryptoapp.data.mapper.toCoinDetail
import com.dev.cryptoapp.data.mapper.toCoinOHCLV
import com.dev.cryptoapp.data.remote.source.ApiResponse
import com.dev.cryptoapp.data.remote.source.RemoteDataSource
import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.model.CoinDetail
import com.dev.cryptoapp.domain.model.CoinOHCLV
import com.dev.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): CoinRepository {
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        when (
            val response = remoteDataSource.getCoins().first()
        ) {
            is ApiResponse.Empty -> {
                emit(Resource.Success(emptyList()))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Success -> {
                emit(Resource.Success(
                    response.data.map { it.toCoin() }
                ))
            }
        }
    }

    override suspend fun getCoinById(coinId: String): Resource<CoinDetail> =
        when(
            val response = remoteDataSource.getCoinDetail(coinId)
        ) {
            is ApiResponse.Empty -> {
                Resource.Error("Unexpected Api Response!")
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    response.data.toCoinDetail()
                )
            }
        }

    override suspend fun getCoinOHLCV(coinId: String, start: Long, end: Long): Resource<CoinOHCLV> =
        when(
            val response = remoteDataSource.getCoinOHLCV(coinId, start, end)
        ) {
            is ApiResponse.Empty -> {
                Resource.Error("Unexpected Api Response!")
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    response.data.toCoinOHCLV()
                )
            }
        }
}