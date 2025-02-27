package com.gracias.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(modifier: Modifier, viewModel: WishViewModel) {


    Column(
        modifier.fillMaxSize().padding(4.dp)
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = {
                viewModel.getUpdatedTitle(newTitle = it)
            },
            value = viewModel.titleState.value,
            label = { Text("Enter Wish Title") }
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = {
                viewModel.getUpdatedDescription(newDescription = it)
            },
            value = viewModel.descriptionState.value,
            label = { Text("Enter Wish Description") }
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {},
            shape = RectangleShape
        ) {
            Text(
                "Add Wish",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp , bottom = 8.dp)
            )
        }



    }
}