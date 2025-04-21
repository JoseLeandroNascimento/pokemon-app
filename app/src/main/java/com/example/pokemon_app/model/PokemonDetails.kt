package com.example.pokemon_app.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val name: String,
    val sprites: PokemonSprites
)


data class PokemonSprites(
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("front_default") val frontDefault:String
)