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
fun AddScreen(modifier: Modifier, viewModel: WishViewModel, navController: NavHostController) {
  val coroutineScope = rememberCoroutineScope()
  val snackBarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState)  }

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
                    viewModel.getUpdatedTitle(newTitle = it)
                },
                value = viewModel.titleState.value,
                label = { Text("Enter Wish Title") }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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

                enabled = !(viewModel.titleState.value == "" || viewModel.descriptionState.value == ""),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                   viewModel.insertWish(
                        Wish(
                            title = viewModel.titleState.value,
                            description = viewModel.descriptionState.value,
                        )
                    )
                    viewModel.clearField()
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Wish Added Successfully!")
                        navController.navigateUp()
                    }

                },
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


}