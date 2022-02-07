package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import com.joaootavio.android.pokedex_egsys.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemons: Pokemon = Pokemon(results = emptyList()),
    val error: String = ""
)
