package com.joaootavio.android.pokedex_egsys.presentation.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joaootavio.android.pokedex_egsys.R
import com.joaootavio.android.pokedex_egsys.common.Constants.KG
import com.joaootavio.android.pokedex_egsys.common.Constants.METROS
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail

@Composable
fun PokemonDetailDataSection(
    pokemonDetail: PokemonDetail,
    sectionHeight: Dp = 80.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PokemonDetailDataItem(
            dataValue = (pokemonDetail.weight.toFloat()/10),
            dataUnit = KG,
            dataIcon = painterResource(R.drawable.ic_weight),
            modifier = Modifier
                .weight(1f)
        )

        Spacer(
            modifier = Modifier
                .size(width = 1.dp, height = sectionHeight)
                .background(Color.LightGray)
        )

        PokemonDetailDataItem(
            dataValue = (pokemonDetail.height.toFloat()/10),
            dataUnit = METROS,
            dataIcon = painterResource(R.drawable.ic_height),
            modifier = Modifier
                .weight(1f)
        )
    }
}