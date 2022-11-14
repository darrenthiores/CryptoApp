package com.dev.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.dev.cryptoapp.domain.model.TeamMember

@Composable
fun TeamListItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    teamMember: TeamMember
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.h4,
            modifier = childModifier
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.body2.copy(
                fontStyle = FontStyle.Italic
            ),
            modifier = childModifier
        )
    }
}