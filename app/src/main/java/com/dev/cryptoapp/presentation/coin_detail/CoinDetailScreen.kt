package com.dev.cryptoapp.presentation.coin_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
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
import com.dev.cryptoapp.presentation.coin_detail.components.CoinChart
import com.dev.cryptoapp.presentation.coin_detail.components.TeamListItem
import com.dev.cryptoapp.presentation.coin_detail.components.TopSection
import com.dev.cryptoapp.presentation.coin_detail.helper.CoinDummy
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun CoinDetailScreen(
    viewModel: CoinViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                TopSection(
                    modifier = Modifier,
                    childModifier = Modifier
                        .placeholder(
                            visible = state.isCoinLoading || state.coin == null,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    coinDetail = state.coin
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Team Members",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .placeholder(
                            visible = state.isCoinLoading || state.coin == null,
                            highlight = PlaceholderHighlight.fade()
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            val teamMembers =
                if(state.isCoinLoading || state.coin == null)
                    CoinDummy.generateTeamMembers()
                else state.coin!!.team

            items(
                items = teamMembers,
                key = { member -> member.id }
            ) { member ->
                TeamListItem(
                    modifier = Modifier,
                    childModifier = Modifier
                        .placeholder(
                            visible = state.isCoinLoading || state.coin == null,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    teamMember = member
                )

                Divider()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Charts",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .placeholder(
                            visible = state.isCoinLoading || state.coin == null,
                            highlight = PlaceholderHighlight.fade()
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))

                CoinChart(
                    modifier = Modifier
                        .placeholder(
                            visible = state.isCoinLoading || state.coin == null,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    chartClose = state.chartCloses
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