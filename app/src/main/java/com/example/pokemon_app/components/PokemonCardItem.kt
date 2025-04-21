package com.example.pokemon_app.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pokemon_app.config.PokemoHttp
import com.example.pokemon_app.data.PokemonData
import com.example.pokemon_app.model.PokemonDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PokemonCardItem(modifier: Modifier = Modifier, name: String) {

    var pokemon by remember { mutableStateOf<PokemonDetails?>(null) }

    LaunchedEffect(Unit) {

        val api = PokemoHttp.retrofit.create(PokemonData::class.java)
        api.getPokemonByName(name).enqueue(object : Callback<PokemonDetails> {

            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {

                pokemon = response.body()
                Log.i("teste", response.body().toString())
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {

                Log.i("teste", "erro")
            }

        })
    }

    Card {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            pokemon?.let {
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = it.sprites.frontDefault,
                    contentDescription = null
                )
            }
            Text(text = name)
        }
    }
}