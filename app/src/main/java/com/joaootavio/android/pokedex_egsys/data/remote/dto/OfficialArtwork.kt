package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)