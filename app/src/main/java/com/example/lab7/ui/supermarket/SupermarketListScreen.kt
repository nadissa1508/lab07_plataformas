package com.example.lab7.ui.supermarket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun SupermarketListScreen(viewModel: SupermarketViewModel) {
    val items = viewModel.items.collectAsState().value
    LazyColumn {
        items(items) { item ->
            SupermarketItemRow(item)
        }
    }
}

@Composable
fun SupermarketItemRow(item: SupermarketItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(item.itemName, Modifier.weight(1f))
        Text("Qty: ${item.quantity}")
        item.imagePath?.let { imagePath ->
            Image(painter = rememberImagePainter(imagePath), contentDescription = null)
        }
    }
}

