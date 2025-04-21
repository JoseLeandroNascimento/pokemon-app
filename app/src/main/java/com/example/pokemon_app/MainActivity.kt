package com.example.pokemon_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pokemon_app.screen.Home
import com.example.pokemon_app.ui.theme.PokemonappTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Home(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}
