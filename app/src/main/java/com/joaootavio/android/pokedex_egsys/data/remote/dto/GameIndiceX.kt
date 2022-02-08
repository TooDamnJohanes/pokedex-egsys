package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GameIndiceX(
    @SerializedName("game_index")
    val gameIndex: Int,
    @SerializedName("generation")
    val generation: Generation
)