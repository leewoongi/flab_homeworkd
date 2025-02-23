package com.woongi.timework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woongi.timework.ui.theme.TimeworkTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimeworkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val firstList by viewModel.firstItem.collectAsState()
    val secondList by viewModel.secondItem.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "리스트"
                    )
                }

                itemsIndexed(firstList) { index, item ->
                    ListItem(
                        modifier = Modifier.background(Color.Blue.copy(0.4f)),
                        item = item,
                        icon = R.drawable.ic_trash,
                        onClick = { viewModel.throwAway(item) },
                        remove = {}
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "휴지통"
                    )
                }
                itemsIndexed(secondList) { index, item ->
                    ListItem(
                        modifier = Modifier.background(Color.Cyan.copy(0.4f)),
                        item = item,
                        icon = R.drawable.ic_back,
                        remove = { viewModel.remove(item) },
                        onClick = {
                            viewModel.pickUp(item)
                        }
                    )
                }
            }
        }
    }
}
