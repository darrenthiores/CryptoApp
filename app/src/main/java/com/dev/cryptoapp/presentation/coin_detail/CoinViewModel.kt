package com.dev.cryptoapp.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.cryptoapp.domain.use_case.GetCoinOHLCVUseCase
import com.dev.cryptoapp.domain.use_case.GetCoinUseCase
import com.dev.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val getCoinOHLCVUseCase: GetCoinOHLCVUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(CoinDetailConstants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
//            getCoinChartsCloses(coinId)
        }

        _state.value = _state.value.copy(
            chartCloses = (0..24).map {
                Random.nextDouble(0.0, 100.0)
            }
        )
    }

    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isCoinLoading = true
            )
            when(
                val result = getCoinUseCase(coinId = coinId)
            ) {
                is Resource.Loading -> Unit
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isCoinLoading = false,
                        error = result.message ?: "Unexpected Error Occurred"
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        coin = result.data,
                        isCoinLoading = false,
                        error = ""
                    )
                }
            }
        }
    }

    private fun getCoinChartsCloses(coinId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isChartLoading = true
            )
            val results = getCoinOHLCVUseCase(coinId = coinId)
            results.map { result ->
                when(result) {
                    is Resource.Loading -> Unit
                    is Resource.Error -> Unit
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            chartCloses = _state.value.chartCloses + result.data!!.close
                        )
                    }
                }
            }
            _state.value = _state.value.copy(
                isChartLoading = false
            )
        }
    }
}