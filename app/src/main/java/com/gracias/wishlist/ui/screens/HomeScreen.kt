package com.gracias.wishlist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gracias.wishlist.data.local.entities.Wish
import com.gracias.wishlist.ui.viewmodel.WishViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateToEdit: (id: Long) -> Unit,
    onNavigateToAdd: () -> Unit,
    viewModel: WishViewModel
) {
    val wishList = viewModel.allWishes.collectAsState(listOf()).value
    val snackbarHostState = remember {
        SnackbarHostState()
    }


    Scaffold(
        snackbarHost = {
             SnackbarHost(
                 hostState = snackbarHostState
             )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                    onNavigateToAdd


            ) {
                Icon(
                    imageVector = Icons.Rounded.Add, contentDescription = "Add icon"
                )
            }
        }
    ) {

        LazyColumn(

            modifier = modifier
                .fillMaxSize()
        ) {

            items(wishList , key = {it.id} ) { item ->
                WishItem(
                    snackbarHostState = snackbarHostState,
                    wish = item,
                    viewModel = viewModel,
                    onclick = { onNavigateToEdit(item.id) }
                )
            }


        }

    }

}

@Composable
fun WishItem(
    wish: Wish,
    viewModel: WishViewModel,
    onclick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {


    var isVisible by remember { mutableStateOf(true) }

    val offsetX = remember { mutableFloatStateOf(0f) }

    val draggableState = rememberDraggableState { delta ->
        offsetX.floatValue += delta
    }

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX.floatValue,
        animationSpec = tween(durationMillis = 100),
        label = "Swipe Offset"
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isVisible) {
        if (!isVisible) {
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "Wish deleted",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Long
                )

                if (result == SnackbarResult.ActionPerformed) {
                    isVisible = true
                } else {
                    delay(1000)
                    viewModel.deleteWish(wish)
                }

                offsetX.floatValue = 0f
            }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(animationSpec = tween(100))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .clickable {
                        viewModel.deleteWish(wish)
                    }
            )
            Card(
                modifier = Modifier
                    .offset {
                        IntOffset(animatedOffsetX.roundToInt(), 0)
                    }

                    .fillMaxSize()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    .clickable(onClick = onclick)
                    .draggable(state = draggableState, orientation = Orientation.Horizontal),
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)

                ) {
                    Text(
                        text = wish.title,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = wish.description,
                    )
                }

            }
        }
    }
    if (offsetX.floatValue <= -300f) {
        isVisible = false
    }
}