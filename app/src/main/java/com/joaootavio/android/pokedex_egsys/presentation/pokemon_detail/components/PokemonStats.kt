package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joaootavio.android.pokedex_egsys.common.Constants.BASE_STATS
import com.joaootavio.android.pokedex_egsys.common.parseStatToAbbr
import com.joaootavio.android.pokedex_egsys.common.parseStatToColor
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail

@Composable
fun PokemonStats(
    pokemon: PokemonDetail,
    animDelayPerItem: Int = 100,
) {

    val maxBaseStat = remember {
        pokemon.stats.maxOf { it.baseStat }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = BASE_STATS,
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        pokemon.stats.forEach { stat ->
            val index = pokemon.stats.indexOf(stat)
            PokemonStatItem(
                statName = parseStatToAbbr(stat = stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat = stat),
                animDelay = (index+1)*animDelayPerItem
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
        }
    }

}