package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HalfDamageTo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)