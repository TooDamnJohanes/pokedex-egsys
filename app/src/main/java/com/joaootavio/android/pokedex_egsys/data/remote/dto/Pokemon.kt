package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("pokemon")
    val pokemon: PokemonX,
    @SerializedName("slot")
    val slot: Int
)