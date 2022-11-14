package com.dev.cryptoapp.presentation.coin_list.components

import androidx.compose.foundation.clickable
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
import com.dev.cryptoapp.domain.model.Coin

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    coin: Coin
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = childModifier
                .weight(1f)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Text(
            text = if(coin.isActive) "Active" else "InActive",
            color = if(coin.isActive) Color.Green else Color.Red,
            style = MaterialTheme.typography.body2.copy(
                fontStyle = FontStyle.Italic
            ),
            textAlign = TextAlign.End,
            modifier = childModifier
        )
    }
}