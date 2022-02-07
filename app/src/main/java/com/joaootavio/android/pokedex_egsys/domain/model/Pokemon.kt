package com.joaootavio.android.pokedex_egsys.domain.model

import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result

data class Pokemon(
    val results: List<Result>
)