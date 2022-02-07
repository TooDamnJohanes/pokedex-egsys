package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaootavio.android.pokedex_egsys.common.Constants.PARAM_POKEMON_ID
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail
import com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons_details.GetPokemonsDetaisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonsDetaisUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf<PokemonDetailState>(PokemonDetailState())
    val state: State<PokemonDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_POKEMON_ID)?.let { pokemonId ->
            getPokemons(pokemonId = pokemonId)
        }
    }

    private fun getPokemons(pokemonId: String) {
        getPokemonUseCase(pokemonId = pokemonId).onEach { result ->
            when (result) {
                is ResponseApi.Success -> {
                    _state.value = PokemonDetailState(pokemon = result.data ?: PokemonDetail(
                        abilities = emptyList(),
                        baseExperience = 0,
                        forms = emptyList(),
                        height = 0,
                        id = 0,
                        moves = emptyList(),
                        name = "",
                        sprites = null,
                        types = emptyList(),
                        weight = 0
                    )
                    )
                }
                is ResponseApi.Error -> {
                    _state.value = PokemonDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is ResponseApi.Loading -> {
                    _state.value = PokemonDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}