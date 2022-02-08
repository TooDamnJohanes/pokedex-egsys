package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DoubleDamageFrom(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)