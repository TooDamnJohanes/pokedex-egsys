package com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons_by_type

import com.joaootavio.android.pokedex_egsys.common.Constants.ANY_DATA_ERROR
import com.joaootavio.android.pokedex_egsys.common.Constants.UNEXPECTED_ERROR
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.data.remote.dto.PokemonsByTypeDto
import com.joaootavio.android.pokedex_egsys.data.remote.dto.toPokemonsByType
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsByType
import com.joaootavio.android.pokedex_egsys.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsByTypeUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    operator fun invoke(typeId: String): Flow<ResponseApi<PokemonsByType>> = flow {
        try {
            emit(ResponseApi.Loading<PokemonsByType>())
            val pokemonsByTypeDto: PokemonsByTypeDto = repository.getPokemonsByType(typeId = typeId)
            val pokemonsByType: PokemonsByType = pokemonsByTypeDto.toPokemonsByType()
            emit(ResponseApi.Success<PokemonsByType>(data = pokemonsByType))
        } catch (e: HttpException) {
            emit(ResponseApi.Error<PokemonsByType>(message = e.message() ?: UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(ResponseApi.Error<PokemonsByType>(message = ANY_DATA_ERROR))
        }
    }

}