package com.example.tareatresnavegacion.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavManager() {
    var navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "Formulario") {
        // Definiendo rutas
        composable(route = "Formulario") {
            HomeView(navController)
        }
        composable(route = "MessageView/{nombre}/{id}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType}
            )
            ) {
            parametros ->
            var nombre = parametros.arguments?.getString("nombre") ?: ""
            var id = parametros.arguments?.getInt("id") ?: 0
            MessageView(navController, nombre,id)
        }
    }
}