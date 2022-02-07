package com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons

import com.joaootavio.android.pokedex_egsys.common.Constants.ANY_DATA_ERROR
import com.joaootavio.android.pokedex_egsys.common.Constants.UNEXPECTED_ERROR
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.toPokemon
import com.joaootavio.android.pokedex_egsys.domain.model.Pokemon
import com.joaootavio.android.pokedex_egsys.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<ResponseApi<Pokemon>> = flow {
        try {
            emit(ResponseApi.Loading<Pokemon>())
            val pokemonDto: PokemonDto = repository.getPokemons()
            val pokemon = pokemonDto.toPokemon()
            emit(ResponseApi.Success<Pokemon>(data = pokemon))
        } catch (e: HttpException) {
            emit(ResponseApi.Error<Pokemon>(message = e.message() ?: UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(ResponseApi.Error<Pokemon>(message = ANY_DATA_ERROR))
        }
    }
}