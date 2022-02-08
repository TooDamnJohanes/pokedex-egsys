package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joaootavio.android.pokedex_egsys.R
import com.joaootavio.android.pokedex_egsys.common.Constants.NONE
import com.joaootavio.android.pokedex_egsys.common.Constants.POKEMON_LOGO
import com.joaootavio.android.pokedex_egsys.common.SearchAppBarState
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components.FilterType
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components.ListAppBar
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components.PokedexLazyColumn

@ExperimentalComposeUiApi
@Composable
fun PokemonsListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val newPokemonSearch: String by viewModel.searchState.collectAsState()
    val state = viewModel.state.value
    val stateFilter = viewModel.stateFilter.value
    val isSearchStarting = newPokemonSearch.trim() != ""
    var filtredPokemonsList: List<Result> = emptyList()
    var validPokemonsList: List<Result> = emptyList()
    val searchAppBarState: SearchAppBarState by viewModel.searchAppBarState
    val filterSelected: String by viewModel.filterSelected.collectAsState()

    if (isSearchStarting) {
        if (stateFilter.pokemons.results.isNotEmpty()){
            filtredPokemonsList =
                stateFilter.pokemons.results.filter { it.name.contains(newPokemonSearch.trim()) }
        } else {
            filtredPokemonsList =
                state.pokemons.results.filter { it.name.contains(newPokemonSearch.trim()) }
        }
    }

    if (stateFilter.pokemons.results.isNotEmpty()) {
        validPokemonsList = stateFilter.pokemons.results
    } else {
        validPokemonsList = state.pokemons.results
    }



    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            ListAppBar(
                searchTextState = newPokemonSearch,
                searchAppBarState = searchAppBarState,
                onSearchClicked = {
                    viewModel.onSearchClicked()
                },
                onCloseClicked = {
                    keyboardController?.hide()
                    viewModel.onClosedClicked()
                },
                onTextChanche = { newSearch ->
                    viewModel.onSearchStateChanged(newSearch)
                },
                onSSortClicked = {type ->
                    viewModel.getPokemonsByType(type = type)
                }
            )
        },
        content = {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                    contentDescription = POKEMON_LOGO,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (filterSelected != NONE) {
                    FilterType(type = filterSelected)
                }
                PokedexLazyColumn(
                    pokemonList = if (newPokemonSearch.trim()
                            .isNotEmpty()
                    ) filtredPokemonsList else validPokemonsList,
                    navController = navController
                )
            }
        },
        contentColor = MaterialTheme.colors.background
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun PokemonsListScreenPreview() {
    val navController = rememberNavController()
    PokemonsListScreen(
        navController = navController
    )
}