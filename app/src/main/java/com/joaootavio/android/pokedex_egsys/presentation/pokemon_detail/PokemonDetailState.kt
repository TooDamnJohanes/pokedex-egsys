package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail

import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetail? = null,
    val error: String = ""
)
