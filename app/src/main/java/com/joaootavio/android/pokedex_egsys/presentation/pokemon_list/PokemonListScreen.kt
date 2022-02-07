package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joaootavio.android.pokedex_egsys.R
import com.joaootavio.android.pokedex_egsys.common.Constants.POKEMON_LOGO
import com.joaootavio.android.pokedex_egsys.common.Constants.SEARCH_POKEMON
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components.PokedexLazyColumn
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components.SearchBar

@ExperimentalComposeUiApi
@Composable
fun PokemonsListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val newPokemonSearch: String by viewModel.searchState.collectAsState()
    val state = viewModel.state.value
    val isSearchStarting = newPokemonSearch.trim() != ""
    var filtredPokemonsList: List<Result> = emptyList()

    if (isSearchStarting) {
        filtredPokemonsList = state.pokemons.results.filter { it.name.contains(newPokemonSearch.trim()) }
    }


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = POKEMON_LOGO,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            SearchBar(
                hint = SEARCH_POKEMON,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                pokemonSearch = newPokemonSearch,
                onPokemonSearchChanged = { newSearch ->
                    viewModel.onSearchStateChanged(
                        newSearch = newSearch
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PokedexLazyColumn(
                pokemonList = if(newPokemonSearch.trim().isNotEmpty()) filtredPokemonsList else state.pokemons.results,
                navController = navController
            )
        }
    }
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