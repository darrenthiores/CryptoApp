package com.dev.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dev.cryptoapp.domain.model.CoinDetail

@Composable
fun CoinSection(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    coin: CoinDetail?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CoinHeader(
            modifier = Modifier,
            childModifier = childModifier,
            rank = coin?.rank ?: 0,
            name = coin?.name ?: "PlaceHolder Name",
            symbol = coin?.symbol ?: "PHS",
            isActive = coin?.isActive ?: false
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = coin?.description ?: "PlaceHolder Description PlaceHolder Description PlaceHolder Description PlaceHolder Description",
            style = MaterialTheme.typography.body2,
            modifier = childModifier
        )
    }
}

@Composable
fun CoinHeader(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    rank: Int,
    name: String,
    symbol: String,
    isActive: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$rank. $name ($symbol)",
            style = MaterialTheme.typography.h2,
            overflow = TextOverflow.Ellipsis,
            modifier = childModifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if(isActive) "Active" else "InActive",
            color = if(isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            modifier = childModifier
        )
    }
}