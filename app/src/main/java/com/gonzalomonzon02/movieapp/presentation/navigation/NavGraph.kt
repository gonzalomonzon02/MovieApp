package com.gonzalomonzon02.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.gonzalomonzon02.movieapp.presentation.screens.main.routeMainScreen
import com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.routeMovieDetailScreen
import com.gonzalomonzon02.movieapp.presentation.screens.splash.routeSplashScreen
import kotlinx.serialization.Serializable

sealed class SubGraph {
    @Serializable
    data object SplashGraph : SubGraph()

    @Serializable
    data object MainGraph : SubGraph()
}

sealed class Destination {

    //Splash
    @Serializable
    data object SplashScreen : Destination()

    //Main
    @Serializable
    data object MainScreen : Destination()

    @Serializable
    data class MovieDetailScreen(val movieId: Int) : Destination()
}

@Composable
fun MovieNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = SubGraph.SplashGraph) {
        navigation<SubGraph.SplashGraph>(startDestination = Destination.SplashScreen) {
            routeSplashScreen(navController)
        }

        navigation<SubGraph.MainGraph>(startDestination = Destination.MainScreen) {
            routeMainScreen(navController)
            routeMovieDetailScreen(navController)
        }
    }
} 