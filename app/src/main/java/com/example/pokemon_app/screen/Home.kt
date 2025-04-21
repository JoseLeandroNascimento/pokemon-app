package com.example.pokemon_app.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemon_app.components.PokemonCardItem
import com.example.pokemon_app.config.PokemoHttp
import com.example.pokemon_app.data.PokemonData
import com.example.pokemon_app.data.ResponsePage
import com.example.pokemon_app.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Home(modifier: Modifier = Modifier) {

    var pokemons by remember { mutableStateOf<ResponsePage<Pokemon>?>(null) }

    LaunchedEffect(Unit) {

        val api = PokemoHttp.retrofit.create(PokemonData::class.java)
        api.getPokemon().enqueue(object : Callback<ResponsePage<Pokemon>> {

            override fun onResponse(
                call: Call<ResponsePage<Pokemon>>,
                response: Response<ResponsePage<Pokemon>>
            ) {
                pokemons = response.body()
            }

            override fun onFailure(call: Call<ResponsePage<Pokemon>>, t: Throwable) {

                Log.i("teste", "erro")
            }

        })
    }

    LazyVerticalGrid(
        modifier = modifier
            .padding(horizontal = 2.dp)
            .fillMaxSize()
        ,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        pokemons?.let { data ->
            items(data.results) {
                PokemonCardItem(name = it.name)
            }
        }
    }

}