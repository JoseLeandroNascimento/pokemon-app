package com.example.pokemon_app.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.pokemon_app.config.PokemoHttp
import com.example.pokemon_app.data.PokemonData
import com.example.pokemon_app.model.PokemonDetails
import com.example.pokemon_app.ui.theme.Red80
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Details(modifier: Modifier = Modifier, name: String, navController: NavHostController? = null) {

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

    Card(
        modifier = modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pokemon?.let { dado ->
                AsyncImage(
                    modifier = Modifier.size(200.dp),
                    model = dado.sprites.other.showdown.frontDefault,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red80,
                    ),
                    onClick = {
                        navController?.popBackStack()
                    }
                ) {
                    Text(
                        text = "Voltar",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}