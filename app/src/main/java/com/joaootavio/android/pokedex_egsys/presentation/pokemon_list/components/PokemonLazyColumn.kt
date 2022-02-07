package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result

@Composable
fun PokedexLazyColumn(
    pokemonList: List<Result>,
    navController: NavController
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        val itemCount = if (pokemonList.size %2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {

            PokedexRow(
                rowIndex = it,
                entries = pokemonList,
                navController = navController
            )
        }
    }
}
