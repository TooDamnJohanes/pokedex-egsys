package com.joaootavio.android.pokedex_egsys.domain.model

import com.joaootavio.android.pokedex_egsys.data.remote.dto.Pokemon

data class PokemonsByType(
    val pokemon: List<Pokemon>
)
