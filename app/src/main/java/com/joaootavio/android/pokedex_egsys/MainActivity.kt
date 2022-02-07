package com.joaootavio.android.pokedex_egsys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.joaootavio.android.pokedex_egsys.presentation.navigation.navigation.PokemonNavigation
import com.joaootavio.android.pokedex_egsys.presentation.ui.theme.PokedexegsysTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexegsysTheme {
                PokedexEgSys()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun PokedexEgSys() {

    Surface(
        color = MaterialTheme.colors.background,
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonNavigation()
        }
    }

}
