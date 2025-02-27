package com.gracias.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController , viewModel: WishViewModel) {
    NavHost(

        navController = navController,
        startDestination = HomeScreen
    ){

        composable<HomeScreen> {

            HomeScreen(
                modifier = modifier,
                onNavigateToWishlistScreen = {
                    navController.navigate(
                        route = DetailScreen
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

        composable<DetailScreen> {
            DetailScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }

    }

}