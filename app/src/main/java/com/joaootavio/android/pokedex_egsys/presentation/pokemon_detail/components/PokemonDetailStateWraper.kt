package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail.components

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail

@Composable
fun PokemonDetailStateWraper(
    pokemon: PokemonDetail,
    modifier: Modifier = Modifier,
) {
    PokemonDetailSection(
        pokemonDetail = pokemon,
        modifier = modifier
            .offset(y = ((-20).dp))
    )
}