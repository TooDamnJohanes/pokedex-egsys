package com.joaootavio.android.pokedex_egsys.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joaootavio.android.pokedex_egsys.R
import com.joaootavio.android.pokedex_egsys.common.Constants.POKEDEX_EGSYS
import com.joaootavio.android.pokedex_egsys.common.Constants.POKEMON_LOGO
import com.joaootavio.android.pokedex_egsys.presentation.navigation.screens.Screen
import com.joaootavio.android.pokedex_egsys.presentation.ui.theme.RobotoCondensed
import kotlinx.coroutines.delay

@Composable
fun PokedexSplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec =
            tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(2000L)
        navController.navigate(Screen.PokemonListScreen.name){
            popUpTo(Screen.SplashScreen.name){
                inclusive = true
            }
        }
    }
    Surface(
        modifier =
        Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.Transparent,
    ) {
        Column(
            modifier =
            Modifier
                .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PokedexLogo()
            Spacer(
                modifier =
                Modifier
                    .height(15.dp)
            )
            Text(
                text = POKEDEX_EGSYS,
                style = MaterialTheme.typography.h5,
                color = Color.Black.copy(alpha = 0.7f),
                fontFamily = RobotoCondensed,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PokedexLogo() {
    Image(
        painter = painterResource(R.drawable.ic_international_pok_mon_logo),
        contentDescription = POKEMON_LOGO,
        modifier = Modifier
            .padding(16.dp)
    )
}