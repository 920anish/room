package com.gracias.wishlist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateToWishlistScreen: (title: String, description: String) -> Unit,
    onNavigateToAdd: () -> Unit
) {
    val wishList = listOf(
        Wish(1 , "Pixel 9 Pro XL" , "All in for the best software Experience"),
        Wish(2 , "Mechanical Gaming Keyboard" , "All in for the best Clickity Click Experience"),
        Wish(3 , "RTX 5090" , "All in for the best VRAM Experience"),

    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                    onNavigateToAdd


            ) {
                Icon(
                    imageVector = Icons.Rounded.Add , contentDescription = "Add icon"
                )
            }
        }
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            items(wishList) { item ->
                WishItem(
                    wish = item
                ) { onNavigateToWishlistScreen(item.title, item.description) }
            }


        }

    }

    }






@Composable
fun WishItem(wish:Wish , onclick : () ->Unit ) {
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable(onClick = onclick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        )
    ) {

        Column (
            modifier = Modifier.padding(16.dp)

        ){
            Text(
                text = wish.title,
                fontWeight = FontWeight.ExtraBold,
            )

            Text(
                text = wish.description,
            )
        }

    }

}