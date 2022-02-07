package com.joaootavio.android.pokedex_egsys.data.repository

import com.joaootavio.android.pokedex_egsys.data.remote.PokemonApi
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDetailDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDto
import com.joaootavio.android.pokedex_egsys.domain.repository.PokemonRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
): PokemonRepository {
    override suspend fun getPokemons(): PokemonDto {
        return api.getPokemons()
    }

    override suspend fun getPokemonById(pokemonId: String): PokemonDetailDto {
        return api.getPokemonById(pokemonId = pokemonId)
    }
}