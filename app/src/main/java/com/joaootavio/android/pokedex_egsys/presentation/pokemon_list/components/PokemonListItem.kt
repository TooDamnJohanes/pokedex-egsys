package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.joaootavio.android.pokedex_egsys.data.remote.dto.Result
import com.joaootavio.android.pokedex_egsys.presentation.navigation.screens.Screen
import com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.PokemonListViewModel
import com.joaootavio.android.pokedex_egsys.presentation.ui.theme.RobotoCondensed


@Composable
fun PokemonListItem(
    pokemonItem: Result,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonName = pokemonItem.name.capitalize()
    val number = if(pokemonItem.url.endsWith("/")) {
        pokemonItem.url.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        pokemonItem.url.takeLastWhile { it.isDigit() }
    }
    val pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "${Screen.PokemonDetailScreen}/${dominantColor.toArgb()}/${pokemonName}"
                )
            }

    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ) {
            val painter = rememberImagePainter(
                data = pokemonImageUrl,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            )
            (painter.state as? ImagePainter.State.Success)
                ?.let { successState ->
                    LaunchedEffect(Unit) {
                        val drawable = successState.result.drawable
                        viewModel.calculateDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    }
                }

            Image(
                painter = painter,
                contentDescription = pokemonItem.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Text(
                text = pokemonName,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}