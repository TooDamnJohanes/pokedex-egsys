package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail.components.PokemonDetailStateWraper
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail.components.PokemonDetailTopSection

@Composable
fun DetailPokemonScreen(
    dominantColor: Color,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(
                bottom = 16.dp
            )
    ) {
        PokemonDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
        state.pokemon?.let { pokemonDetail ->
            PokemonDetailStateWraper(
                pokemon = pokemonDetail,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top =  topPadding + pokemonImageSize/2f,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            state.pokemon?.let { pokemonDetail ->
                val painter = rememberImagePainter(
                    data = pokemonDetail.sprites?.frontDefault,
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = pokemonDetail.name,
                    modifier = Modifier
                        .size(pokemonImageSize)
                        .offset(y = topPadding),
                )
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

    }
}

