package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail

data class PokemonDetailDto(
    @SerializedName("abilities")
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("stats")
    val stats: List<Stat>,
    @SerializedName("types")
    val types: List<Type>,
    @SerializedName("weight")
    val weight: Int
)

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        abilities = abilities,
        baseExperience = baseExperience,
        forms = forms,
        height = height,
        id = id,
        moves = moves,
        name = name,
        sprites = sprites,
        types = types,
        weight = weight
    )
}