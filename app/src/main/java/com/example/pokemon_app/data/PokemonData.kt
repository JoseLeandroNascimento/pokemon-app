package com.example.pokemon_app.data

import com.example.pokemon_app.model.Pokemon
import com.example.pokemon_app.model.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonData {

    @GET("pokemon")
    fun getPokemon():Call<ResponsePage<Pokemon>>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name:String): Call<PokemonDetails>
}