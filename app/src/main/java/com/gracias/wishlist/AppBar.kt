package com.gracias.wishlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String , onNavigationBack: () -> Unit) {

    TopAppBar(

        title = { Text(title) },
        modifier = Modifier
            .padding(start = 2.dp),


        navigationIcon = {

                IconButton(onClick = onNavigationBack ) {
                    Icon(
                        imageVector =   Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back arrow",
                    )

                }


        }

    )

}