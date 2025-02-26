package com.gracias.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ){

        composable<HomeScreen> {

            HomeScreen(
                modifier = modifier,
                onNavigateToWishlistScreen = {
                    navController.navigate(
                        route = WishScreen
                    )
                },
            )


        }
        composable<WishScreen> {

            WishScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                modifier = modifier
            )


        }

    }

}