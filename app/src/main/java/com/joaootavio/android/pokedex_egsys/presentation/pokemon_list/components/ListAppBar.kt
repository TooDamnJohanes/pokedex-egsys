package com.joaootavio.android.pokedex_egsys.presentation.pokemon_list.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joaootavio.android.pokedex_egsys.common.Constants.BUG
import com.joaootavio.android.pokedex_egsys.common.Constants.CLOSE_ICON
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
import com.joaootavio.android.pokedex_egsys.common.Constants.LIST_ICON
import com.joaootavio.android.pokedex_egsys.common.Constants.NONE
import com.joaootavio.android.pokedex_egsys.common.Constants.NORMAL
import com.joaootavio.android.pokedex_egsys.common.Constants.POISON
import com.joaootavio.android.pokedex_egsys.common.Constants.POKEDEX_EGSYS
import com.joaootavio.android.pokedex_egsys.common.Constants.PSYCHIC
import com.joaootavio.android.pokedex_egsys.common.Constants.ROCK
import com.joaootavio.android.pokedex_egsys.common.Constants.SEARCH_ICON
import com.joaootavio.android.pokedex_egsys.common.Constants.SEARCH_POKEMON
import com.joaootavio.android.pokedex_egsys.common.Constants.STEEL
import com.joaootavio.android.pokedex_egsys.common.Constants.WATER
import com.joaootavio.android.pokedex_egsys.common.SearchAppBarState

@ExperimentalComposeUiApi
@Composable
fun ListAppBar(
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
    onSearchClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onTextChanche: (String) -> Unit,
    onSSortClicked: (String) -> Unit
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = onSearchClicked,
                onSSortClicked = onSSortClicked,
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChanche = onTextChanche,
                onClosedClick = onCloseClicked,
            )
        }
    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSSortClicked: (String) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = POKEDEX_EGSYS,
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSSortClicked = onSSortClicked,
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSSortClicked: (String) -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortedClicked = onSSortClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = SEARCH_ICON,
        )
    }
}

@Composable
@Preview
fun SortAction(
    onSortedClicked: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val listOfTypes = listOf(
        NONE,
        NORMAL,
        FIGHTING,
        FLYING,
        POISON,
        GROUND,
        ROCK,
        BUG,
        GHOST,
        STEEL,
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        PSYCHIC,
        ICE,
        DRAGON,
        DARK,
        FAIRY
    )
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = LIST_ICON,
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOfTypes.forEach { type ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSortedClicked(type)
                }) {
                    Text(
                        text = type
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    text: String = "",
    onTextChanche: (String) -> Unit = {},
    onClosedClick: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.background
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { searchText ->
                if (searchText.length <= 30) {
                    onTextChanche(searchText)
                }
            },
            placeholder = {
                Text(
                    modifier =
                    Modifier
                        .alpha(ContentAlpha.medium),
                    text = SEARCH_POKEMON,
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = SEARCH_ICON,
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChanche("")
                        } else {
                            onClosedClick()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = CLOSE_ICON,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            )
        )
    }
}



