package com.dev.cryptoapp.presentation.coin_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.cryptoapp.presentation.coin_list.components.CoinListItem
import com.dev.cryptoapp.presentation.coin_list.helper.CoinsDummy
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val coins =
        if(
            state.isLoading ||
            state.coins.isEmpty() ||
            state.error.isNotBlank()
        ) CoinsDummy.generateCoins() else state.coins

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                items = coins,
                key = { coin -> coin.id }
            ) { coin ->
                CoinListItem(
                    modifier = Modifier
                        .clickable {
                            if(
                                !state.isLoading &&
                                state.error.isBlank()
                            ) {
                                navigateToDetail(coin.id)
                            }
                        },
                    childModifier = Modifier
                        .placeholder(
                            visible = state.isLoading || state.error.isNotBlank(),
                            highlight = PlaceholderHighlight.fade()
                        ),
                    coin = coin
                )
            }
        }

        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}