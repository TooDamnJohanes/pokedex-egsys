package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsList

data class PokemonDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)

fun PokemonDto.toPokemon(): PokemonsList {
    return PokemonsList(
        results = results
    )
}