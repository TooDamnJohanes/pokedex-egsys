package com.joaootavio.android.pokedex_egsys.data.remote

import com.joaootavio.android.pokedex_egsys.common.Constants.GET_POKEMONS_END_POINT
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDetailDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET(GET_POKEMONS_END_POINT)
    suspend fun getPokemons(): PokemonDto

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonById(@Path("pokemonId") pokemonId: String): PokemonDetailDto

}