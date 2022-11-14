package com.dev.cryptoapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dev.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.dev.cryptoapp.presentation.coin_list.CoinListScreen
import com.dev.cryptoapp.presentation.state.AppState
import com.dev.cryptoapp.presentation.state.rememberAppState

@Composable
fun CryptoApp(
    appState: AppState = rememberAppState()
) {
    Scaffold(
        scaffoldState = appState.scaffoldState
    ) { paddingValues ->
        CryptoNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = appState.navController
        )
    }
}

@Composable
fun CryptoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.CoinList.name
    ) {
        composable(Screen.CoinList.name) {
            CoinListScreen(
                navigateToDetail = { coinId ->
                    navController.navigate("${Screen.CoinDetail.name}/$coinId")
                }
            )
        }
        composable(
            route = "${Screen.CoinDetail.name}/{coinId}"
        ) {
            CoinDetailScreen()
        }
    }
}