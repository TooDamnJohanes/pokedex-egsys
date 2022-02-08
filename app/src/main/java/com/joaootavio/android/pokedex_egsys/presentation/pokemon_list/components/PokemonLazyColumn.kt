package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result

@ExperimentalFoundationApi
@Composable
fun PokedexLazyColumn(
    pokemonList: List<Result>,
    navController: NavController
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2),
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(pokemonList) { pokemon ->
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight()
                    .wrapContentWidth()
            ) {
                PokemonListItem(
                    pokemonItem = pokemon,
                    navController = navController,
                )
            }
        }
    }
}
