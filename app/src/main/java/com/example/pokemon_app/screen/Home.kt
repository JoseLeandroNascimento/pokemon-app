package com.example.pokemon_app.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemon_app.components.PokemonCardItem
import com.example.pokemon_app.config.PokemoHttp
import com.example.pokemon_app.data.PokemonData
import com.example.pokemon_app.data.ResponsePage
import com.example.pokemon_app.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController? = null) {

    var pokemons by remember { mutableStateOf<ResponsePage<Pokemon>?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        isLoading = true

        val api = PokemoHttp.retrofit.create(PokemonData::class.java)
        api.getPokemon().enqueue(object : Callback<ResponsePage<Pokemon>> {

            override fun onResponse(
                call: Call<ResponsePage<Pokemon>>,
                response: Response<ResponsePage<Pokemon>>
            ) {

                isLoading = false
                pokemons = response.body()
            }

            override fun onFailure(call: Call<ResponsePage<Pokemon>>, t: Throwable) {

                isLoading = false
                Log.i("teste", "erro")
            }

        })
    }

    fun verDetalhe(name: String) {
        navController?.navigate("details/${name}")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.Center

    ) {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            pokemons?.let { data ->
                items(data.results) {
                    PokemonCardItem(name = it.name, onClick = { verDetalhe(it) })
                }
            }
        }

        if(isLoading){
            CircularProgressIndicator()
        }


    }


}