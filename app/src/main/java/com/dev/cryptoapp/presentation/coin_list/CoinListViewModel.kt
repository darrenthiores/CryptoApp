package com.dev.cryptoapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.cryptoapp.domain.model.Coin
import com.dev.cryptoapp.domain.use_case.GetCoinsUseCase
import com.dev.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { result ->
                    when(result) {
                        is Resource.Loading -> {
                            _state.value = CoinListState(
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            _state.value = CoinListState(
                                error = result.message ?: "Unexpected Error Occurred"
                            )
                        }
                        is Resource.Success -> {
                            _state.value = CoinListState(
                                coins = result.data ?: emptyList()
                            )
                        }
                    }
                }
        }
    }
}