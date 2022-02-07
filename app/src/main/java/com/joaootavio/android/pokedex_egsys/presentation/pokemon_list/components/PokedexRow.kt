package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<Result>,
    navController: NavController
) {
    Column {
        Row {
            PokemonListItem(
                pokemonItem = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            if (entries.size >= rowIndex * 2 + 2) {
                PokemonListItem(
                    pokemonItem = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier
                        .weight(1f)
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }
}