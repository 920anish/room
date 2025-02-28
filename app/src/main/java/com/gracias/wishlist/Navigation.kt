package com.gracias.wishlist

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController , viewModel: WishViewModel) {
    NavHost(

        navController = navController,
        startDestination = Home,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }

    ){
        composable<Home> {
            HomeScreen(
                modifier = modifier,
                onNavigateToWishlistScreen = {
                    title ,description ->
                    viewModel.getEditedTitle(title)
                    viewModel.getEditedDescription(description)
                    navController.navigate(
                        route = Edit
                    )
                },
            )


        }


        composable<Add> {
            AddScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }

        composable<Edit> {
            EditScreen(
                modifier = modifier,
                viewModel = viewModel,
            )
        }

    }

}