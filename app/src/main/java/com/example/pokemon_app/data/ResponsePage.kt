package com.example.pokemon_app.data

//"count": 367,
//"next": "https://pokeapi.co/api/v2/ability/?offset=20&limit=20",
//"previous": null,
//"results": [
//{

data class ResponsePage<T>(
    val count:Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)