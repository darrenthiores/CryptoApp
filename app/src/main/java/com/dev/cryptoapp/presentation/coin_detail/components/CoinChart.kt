package com.dev.cryptoapp.presentation.coin_detail.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun CoinChart(
    modifier: Modifier = Modifier,
    chartClose: List<Double>
) {
    val width = LocalConfiguration.current.screenWidthDp
    val height = (width/2f).dp

    val highestChart = chartClose.maxOrNull()

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            var reversedIndex = chartClose.size
            val path = Path().apply {
                chartClose.forEachIndexed { index, close ->
                    val x = maxWidth.toPx()/reversedIndex
                    val y = maxHeight.toPx() - (maxHeight.toPx() * (close/highestChart!!).toFloat())

                    if(index==0) {
                        moveTo(0f, y)
                    } else {
                        lineTo(x, y)
                    }

                    reversedIndex--
                }
            }

            val pathColor = if(chartClose[0] > chartClose.last()) Color.Red else Color.Green

            drawPath(
                path = path,
                color = pathColor,
                style = Stroke(
                    width = 2.dp.toPx()
                )
            )
        }
    }
}