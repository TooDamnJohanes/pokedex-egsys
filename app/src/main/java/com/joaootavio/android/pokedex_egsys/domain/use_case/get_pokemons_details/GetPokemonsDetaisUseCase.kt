package com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons_details

import com.joaootavio.android.pokedex_egsys.common.Constants.ANY_DATA_ERROR
import com.joaootavio.android.pokedex_egsys.common.Constants.UNEXPECTED_ERROR
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonDetailDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.toPokemonDetail
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail
import com.joaootavio.android.pokedex_egsys.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsDetaisUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    operator fun invoke(pokemonId: String): Flow<ResponseApi<PokemonDetail>> = flow {
        try {
            emit(ResponseApi.Loading<PokemonDetail>())
            val pokemonDto: PokemonDetailDto = repository.getPokemonById(pokemonId = pokemonId)
            val pokemon: PokemonDetail = pokemonDto.toPokemonDetail()
            emit(ResponseApi.Success<PokemonDetail>(data = pokemon))
        } catch (e: HttpException) {
            emit(ResponseApi.Error<PokemonDetail>(message = e.message() ?: UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(ResponseApi.Error<PokemonDetail>(message = ANY_DATA_ERROR))
        }
    }

}