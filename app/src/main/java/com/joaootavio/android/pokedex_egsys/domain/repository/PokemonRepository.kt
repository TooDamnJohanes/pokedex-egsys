package com.joaootavio.android.pokedex_egsys.domain.repository

import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDetailDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDto

interface PokemonRepository {

    suspend fun getPokemons(): PokemonDto

    suspend fun getPokemonById(pokemonId: String): PokemonDetailDto

}