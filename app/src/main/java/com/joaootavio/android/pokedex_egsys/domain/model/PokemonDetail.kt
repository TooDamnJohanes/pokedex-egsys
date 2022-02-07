package com.joaootavio.android.pokedex_egsys.domain.model

import com.joaootavio.android.pokedex_egsys.data.remote.dto.*

data class PokemonDetail(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val sprites: Sprites?,
    val types: List<Type>,
    val weight: Int
)
