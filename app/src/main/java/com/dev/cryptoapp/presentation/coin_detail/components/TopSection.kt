package com.dev.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.cryptoapp.domain.model.CoinDetail
import com.dev.cryptoapp.presentation.coin_detail.helper.CoinDummy

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    coinDetail: CoinDetail?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CoinSection(
            modifier = Modifier,
            childModifier = childModifier,
            coin = coinDetail
        )

        Spacer(modifier = Modifier.height(16.dp))

        TagSection(
            modifier = Modifier,
            childModifier = childModifier,
            tags = coinDetail?.tags ?: CoinDummy.generateTags()
        )
    }
}