package com.gracias.wishlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen( modifier: Modifier, onNavigateToWishlistScreen: () -> Unit) {
    val wishList = listOf(
        Wish(1 , "Pixel 9 Pro XL" , "All in for the best software Experience"),
        Wish(2 , "Mechanical Gaming Keyboard" , "All in for the best Clickity Click Experience"),
        Wish(3 , "RTX 5090" , "All in for the best VRAM Experience"),

    )

   LazyColumn (
       modifier = modifier.fillMaxSize().padding(4.dp)
   ){
       items(wishList) {
           item -> WishItem(wish =  item , onNavigateToWishlistScreen)
       }


   }

}




@Composable
fun WishItem(wish:Wish , onclick : () ->Unit ) {

    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 8.dp , start = 8.dp , end = 8.dp)
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