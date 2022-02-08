package com.joaootavio.android.pokedex_egsys.domain.repository

import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDetailDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonsByTypeDto

interface PokemonRepository {

    suspend fun getPokemons(): PokemonDto

    suspend fun getPokemonById(pokemonId: String): PokemonDetailDto

    suspend fun getPokemonsByType(typeId: String): PokemonsByTypeDto

}