package com.example.pokemon_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemon_app.screen.Details
import com.example.pokemon_app.screen.Home

@Composable
fun Router(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(modifier, navController)
        }
        composable("details/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            Details(modifier, name, navController)
        }
    }
}