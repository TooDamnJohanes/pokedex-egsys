package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.joaootavio.android.pokedex_egsys.common.Constants.UNEXPECTED_ERROR
import com.joaootavio.android.pokedex_egsys.common.ResponseApi
import com.joaootavio.android.pokedex_egsys.domain.model.Pokemon
import com.joaootavio.android.pokedex_egsys.domain.use_case.get_pokemons.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
): ViewModel() {

    private val _state = mutableStateOf<PokemonListState>(PokemonListState())
    val state: State<PokemonListState> = _state

    private val _searchState: MutableStateFlow<String> = MutableStateFlow<String>("")
    val searchState: StateFlow<String> = _searchState

    init {
        getPokemons()
    }

    private fun getPokemons() {
        getPokemonsUseCase().onEach { result ->
            when (result) {
                is ResponseApi.Success -> {
                    _state.value = PokemonListState(pokemons = result.data ?: Pokemon(results = emptyList()))
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

    fun onSearchStateChanged(newSearch: String) {
        _searchState.value = newSearch
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitMap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitMap).generate{ palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}