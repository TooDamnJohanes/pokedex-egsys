package com.joaootavio.android.pokedex_egsys.presentation.navigation.screens

import com.joaootavio.android.pokedex_egsys.common.Constants.IS_NOT_RECOGNIZED
import com.joaootavio.android.pokedex_egsys.common.Constants.ROUTE

enum class Screen {
    SplashScreen,
    PokemonListScreen,
    PokemonDetailScreen;

    companion object {
        fun fromRoute(route: String?): Screen
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            PokemonListScreen.name -> PokemonListScreen
            PokemonDetailScreen.name -> PokemonDetailScreen
            null -> PokemonListScreen
            else -> throw IllegalArgumentException("$ROUTE $route $IS_NOT_RECOGNIZED")
        }
    }
}