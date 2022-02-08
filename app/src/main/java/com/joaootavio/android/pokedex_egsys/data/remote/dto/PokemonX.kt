package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsByType

data class PokemonX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonX.toResult(): Result {
    return Result(
        name = name,
        url = url
    )
}