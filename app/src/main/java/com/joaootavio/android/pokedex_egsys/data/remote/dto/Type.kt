package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeX
)