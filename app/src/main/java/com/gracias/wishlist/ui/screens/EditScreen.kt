package com.gracias.wishlist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gracias.wishlist.data.local.entities.Wish
import com.gracias.wishlist.ui.viewmodel.WishViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    modifier: Modifier,
    viewModel: WishViewModel,
    id: Long,
    navController: NavHostController
) {

val wish = viewModel.getWishById(id).collectAsState(initial = Wish()).value

LaunchedEffect(wish) {
    viewModel.getEditedTitle(wish.title)
    viewModel.getEditedDescription(wish.description)
}

val coroutineScope = rememberCoroutineScope()
val snackbarHostState =remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {
                    viewModel.getEditedTitle(newTitle = it)
                },
                value = viewModel.editTitleState.value,
                label = { Text("Update Wish Title") }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {
                    viewModel.getEditedDescription(newDescription = it)
                },
                value =viewModel.editDescriptionState.value,
                label = { Text("Update Wish Description") }
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Button(
                enabled = viewModel.editTitleState.value.isNotBlank() && viewModel.editDescriptionState.value.isNotBlank() && (viewModel.editTitleState.value != wish.title || viewModel.editDescriptionState.value != wish.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    viewModel.updateWish(wish.copy(
                        title = viewModel.editTitleState.value,
                        description = viewModel.editDescriptionState.value
                    ))
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Wish Updated Successfully!")
                        navController.navigateUp()
                    }
                },
                shape = RectangleShape
            ) {
                Text(
                    "Update Wish",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp , bottom = 8.dp)
                )
            }




        }

    }


}