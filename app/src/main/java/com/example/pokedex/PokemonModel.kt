package com.example.pokedex

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    var id: Int,
    var types: List<TypesContainerModel>,
    var name: String,
    var stats: List<Stats>
)

@Serializable
data class TypesContainerModel(
    var type: TypeModel
)

@Serializable
data class TypeModel(
    var name: String
)
@Serializable
data class Stats (
    val base_stat: Int
)










