package com.example.pokedex

data class DetailModel(
        val stats: List<Numbers>
)

data class Numbers (
        val base_stat: Int
        )