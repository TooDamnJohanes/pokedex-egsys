package com.joaootavio.android.pokedex_egsys.presentation.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.joaootavio.android.pokedex_egsys.presentation.navigation.screens.Screen
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.PokemonsListScreen

@ExperimentalComposeUiApi
@Composable
fun PokemonNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.PokemonListScreen.name
    ) {
        composable(Screen.SplashScreen.name) {

        }

        composable(Screen.PokemonListScreen.name) {
            PokemonsListScreen(
                navController = navController
            )
        }

        val pokemonDetail = Screen.PokemonDetailScreen.name
        composable(
            route = "$pokemonDetail/{dominantColor}/{pokemonItemId}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonItemId") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }

            val pokemonItemId = remember {
                it.arguments?.getString("pokemonItemId")
            }
        }
    }
}