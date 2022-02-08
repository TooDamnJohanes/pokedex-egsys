package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.joaootavio.android.pokedex_egsys.common.Constants
import com.joaootavio.android.pokedex_egsys.common.Constants.BUG
import com.joaootavio.android.pokedex_egsys.common.Constants.DARK
import com.joaootavio.android.pokedex_egsys.common.Constants.DRAGON
import com.joaootavio.android.pokedex_egsys.common.Constants.ELECTRIC
import com.joaootavio.android.pokedex_egsys.common.Constants.FAIRY
import com.joaootavio.android.pokedex_egsys.common.Constants.FIGHTING
import com.joaootavio.android.pokedex_egsys.common.Constants.FIRE
import com.joaootavio.android.pokedex_egsys.common.Constants.FLYING
import com.joaootavio.android.pokedex_egsys.common.Constants.GHOST
import com.joaootavio.android.pokedex_egsys.common.Constants.GRASS
import com.joaootavio.android.pokedex_egsys.common.Constants.GROUND
import com.joaootavio.android.pokedex_egsys.common.Constants.ICE
import com.joaootavio.android.pokedex_egsys.common.Constants.NONE
import com.joaootavio.android.pokedex_egsys.common.Constants.NORMAL
import com.joaootavio.android.pokedex_egsys.common.Constants.POISON
import com.joaootavio.android.pokedex_egsys.common.Constants.PSYCHIC
import com.joaootavio.android.pokedex_egsys.common.Constants.ROCK
import com.joaootavio.android.pokedex_egsys.common.Constants.STEEL
import com.joaootavio.android.pokedex_egsys.common.Constants.UNEXPECTED_ERROR
import com.joaootavio.android.pokedex_egsys.common.Constants.WATER
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.common.SearchAppBarState
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result
import com.joaootavio.android.pokedex_egsys.data.remote.dto.toResult
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsList
import com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons_by_type.GetPokemonsByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonsByTypeUseCase: GetPokemonsByTypeUseCase
) : ViewModel() {

    private val _state = mutableStateOf<PokemonListState>(PokemonListState())
    val state: State<PokemonListState> = _state

    private val _stateFilter = mutableStateOf<PokemonListState>(PokemonListState())
    val stateFilter: State<PokemonListState> = _stateFilter

    private val _searchState: MutableStateFlow<String> = MutableStateFlow<String>("")
    val searchState: StateFlow<String> = _searchState

    var filterSelected: MutableStateFlow<String> = MutableStateFlow<String>(NONE)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    init {
        getPokemons()
    }

    fun onSearchClicked() {
        searchAppBarState.value = SearchAppBarState.OPENED
    }

    fun onClosedClicked() {
        if (_searchState.value.trim().isNotEmpty()) {
            _searchState.value == ""
        } else {
            searchAppBarState.value = SearchAppBarState.CLOSED
        }
    }

    fun onSearchStateChanged(newSearch: String) {
        _searchState.value = newSearch
    }

    fun getPokemonsByType(type: String) {
        filterSelected.value = type
        var typeId = 0
        when (type) {
            NONE -> {
                typeId = 0
            }
            NORMAL -> {
                typeId = 1
            }
            FIGHTING -> {
                typeId = 2
            }
            FLYING -> {
                typeId = 3
            }
            POISON -> {
                typeId = 4
            }
            GROUND -> {
                typeId = 5
            }
            ROCK -> {
                typeId = 6
            }
            BUG -> {
                typeId = 7
            }
            GHOST -> {
                typeId = 8
            }
            STEEL -> {
                typeId = 9
            }
            FIRE -> {
                typeId = 10
            }
            WATER -> {
                typeId = 11
            }
            GRASS -> {
                typeId = 12
            }
            ELECTRIC -> {
                typeId = 13
            }
            PSYCHIC -> {
                typeId = 14
            }
            ICE -> {
                typeId = 15
            }
            DRAGON -> {
                typeId = 16
            }
            DARK -> {
                typeId = 17
            }
            FAIRY -> {
                typeId = 18
            }
            else -> {
                typeId = 0
            }
        }
        if (typeId != 0) {
            getPokemonsByTypeUseCase(typeId = typeId.toString()).onEach { result ->
                when (result) {
                    is ResponseApi.Success -> {
                        _state.value = PokemonListState()
                        val pokemonsById: MutableList<Result> = arrayListOf()
                        if (!result.data?.pokemon.isNullOrEmpty()) {
                            result.data?.pokemon?.onEach { pokemon ->
                                val temp = pokemon.pokemon.toResult()
                                pokemonsById.add(temp)
                            }
                        }
                        val pokemonsByIdList = PokemonsList(
                            results = pokemonsById
                        )
                        _stateFilter.value = PokemonListState(
                            pokemons = pokemonsByIdList
                        )
                    }
                    is ResponseApi.Error -> {
                        _state.value = PokemonListState(error = result.message ?: UNEXPECTED_ERROR)
                    }
                    is ResponseApi.Loading -> {
                        _state.value = PokemonListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            getPokemons()
        }
    }

    private fun getPokemons() {
        getPokemonsUseCase().onEach { result ->
            when (result) {
                is ResponseApi.Success -> {
                    _stateFilter.value = PokemonListState()
                    _state.value = PokemonListState(
                        pokemons = result.data ?: PokemonsList(results = emptyList())
                    )
                }
                is ResponseApi.Error -> {
                    _state.value = PokemonListState(error = result.message ?: UNEXPECTED_ERROR)
                }
                is ResponseApi.Loading -> {
                    _state.value = PokemonListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitMap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitMap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}