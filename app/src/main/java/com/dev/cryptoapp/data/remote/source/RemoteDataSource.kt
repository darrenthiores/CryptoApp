package com.dev.cryptoapp.data.remote.source

import com.dev.cryptoapp.util.dispatchers.DispatchersProvider
import com.dev.cryptoapp.data.remote.dto.CoinDetailDto
import com.dev.cryptoapp.data.remote.dto.CoinDto
import com.dev.cryptoapp.data.remote.dto.CoinOHLCVDto
import com.dev.cryptoapp.data.remote.service.CoinPaprikaApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val api: CoinPaprikaApi,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getCoins(): Flow<ApiResponse<List<CoinDto>>> =
        getResponse {
            val response = api.getCoins()

            when {
                response.isEmpty() -> ApiResponse.Empty
                else -> ApiResponse.Success(response)
            }
        }

    suspend fun getCoinDetail(coinId: String): ApiResponse<CoinDetailDto> =
        suspendGetResponse {
            ApiResponse.Success(api.getCoinById(coinId))
        }

    suspend fun getCoinOHLCV(
        coinId: String,
        start: Long,
        end: Long
    ): ApiResponse<CoinOHLCVDto> =
        suspendGetResponse {
            val response = api.getCoinOHLCV(coinId, start, end)

            when {
                response.isEmpty() -> ApiResponse.Empty
                else -> ApiResponse.Success(response[0])
            }
        }

    private fun <T> getResponse(
        httpCall: suspend () -> ApiResponse<T>
    ): Flow<ApiResponse<T>> = flow {
        try {
            emit(httpCall())
        }
        catch (e: HttpException) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(ApiResponse.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
        catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.flowOn(dispatchers.io)

    private suspend fun <T> suspendGetResponse(
        httpCall: suspend () -> ApiResponse<T>
    ): ApiResponse<T> =
        try {
            httpCall()
        }
        catch (e: HttpException) {
            ApiResponse.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
        catch (e: IOException) {
            ApiResponse.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection.")
        }
        catch (e: Exception) {
            ApiResponse.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
}