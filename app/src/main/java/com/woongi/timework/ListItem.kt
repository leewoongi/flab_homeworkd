package com.woongi.timework

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.woongi.timework.ui.model.Item

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    item: Item,
    icon: Int,
    remove: () -> Unit,
    onClick: () -> Unit
) {
    val time by item.timerManager.timerFlow.collectAsState()
    LaunchedEffect(time) { if(time > 3) { remove() } }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier.padding(8.dp),
                text = item.id.toString() + ":" + item.text
            )

            if (time != 0) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = time.toString()
                )
            }
        }

        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Move item"
            )
        }
    }
}