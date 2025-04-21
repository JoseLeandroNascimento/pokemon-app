package com.example.pokemon_app.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemoHttp {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
