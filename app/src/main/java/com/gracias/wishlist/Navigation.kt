package com.gracias.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController , viewModel: WishViewModel) {
    NavHost(

        navController = navController,
        startDestination = Home,
        //meh animation but kinda smooth to not use
//        enterTransition = {
//            EnterTransition.None
//        },
//        exitTransition = {
//            ExitTransition.None
//        },
//        popEnterTransition = {
//            EnterTransition.None
//        },
//        popExitTransition = {
//            ExitTransition.None
//        }

    ){
        composable<Home> {
            HomeScreen(
                viewModel = viewModel,
                modifier = modifier,
                onNavigateToEdit = {

                    navController.navigate(
                        route = Edit(it)
                    )
                },

                onNavigateToAdd = {
                    navController.navigate(
                        route = Add
                    )
                }
            )
        }


        composable<Add> {
            AddScreen(
                navController = navController,
                modifier = modifier,
                viewModel = viewModel
            )
        }

        composable<Edit> {
            val id = it.toRoute<Edit>().id
            EditScreen(
                navController =navController,
                modifier = modifier,
                viewModel = viewModel,
                id = id

            )
        }

    }

}