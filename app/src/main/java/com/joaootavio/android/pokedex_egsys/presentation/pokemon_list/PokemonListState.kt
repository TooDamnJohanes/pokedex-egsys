package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsList

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemons: PokemonsList = PokemonsList(results = emptyList()),
    val error: String = ""
)
