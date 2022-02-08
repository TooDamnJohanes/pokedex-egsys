package com.joaootavio.android.pokedex_egsys.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonDetail
import com.joaootavio.android.pokedex_egsys.domain.model.PokemonsByType

data class PokemonsByTypeDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelations,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndiceX>,
    @SerializedName("generation")
    val generation: GenerationX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("move_damage_class")
    val moveDamageClass: MoveDamageClass,
    @SerializedName("moves")
    val moves: List<MoveXX>,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("past_damage_relations")
    val pastDamageRelations: List<Any>,
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)

fun PokemonsByTypeDto.toPokemonsByType(): PokemonsByType {
    return PokemonsByType(
        pokemon = pokemon
    )
}