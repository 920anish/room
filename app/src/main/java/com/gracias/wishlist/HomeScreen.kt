package com.gracias.wishlist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateToEdit: (id:Long) -> Unit,
    onNavigateToAdd: () -> Unit,
    viewModel: WishViewModel
) {
    val wishList = viewModel.allWishes.collectAsState(listOf()).value



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
                    wish = item,
                    viewModel = viewModel
                ) { onNavigateToEdit(item.id ) }
            }


        }

    }

    }






@Composable
fun WishItem(wish:Wish ,viewModel: WishViewModel, onclick : () ->Unit) {
    val offsetX = remember { mutableFloatStateOf(0f) }

    val draggableState = rememberDraggableState {
            delta ->
        offsetX.value += delta
    }

    if(offsetX.floatValue <= -300f ){
        viewModel.deleteWish(wish)
    }


    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable(onClick = onclick)
        .draggable(state = draggableState , orientation = Orientation.Horizontal ),
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