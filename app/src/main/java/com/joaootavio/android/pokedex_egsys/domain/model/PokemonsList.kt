package com.joaootavio.android.pokedex_egsys.domain.model

import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result

data class PokemonsList(
    val results: List<Result>
)