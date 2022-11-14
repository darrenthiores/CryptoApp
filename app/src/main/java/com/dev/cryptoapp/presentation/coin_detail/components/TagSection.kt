package com.dev.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TagSection(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    tags: List<String>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = childModifier,
            text = "Tags",
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp
        ) {
            tags.forEach {
                CoinTag(
                    modifier = childModifier,
                    tag = it
                )
            }
        }
    }
}

@Composable
fun CoinTag(
    modifier: Modifier = Modifier,
    tag: String
) {
    Text(
        text = tag,
        style = MaterialTheme.typography.body2.copy(
            color = MaterialTheme.colors.primary
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    )
}