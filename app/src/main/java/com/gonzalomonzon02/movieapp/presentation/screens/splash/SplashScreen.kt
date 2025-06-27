package com.gonzalomonzon02.movieapp.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gonzalomonzon02.movieapp.presentation.navigation.Destination
import com.gonzalomonzon02.movieapp.presentation.theme.MovieAppTheme
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.routeSplashScreen(navController: NavController) {
    composable<Destination.SplashScreen> {
        val viewModel: SplashViewModel = koinViewModel()

        LaunchedEffect(Unit) {
            viewModel.navigateToNextScreen.collect {
                navController.navigate(Destination.MainScreen) {
                    popUpTo(Destination.SplashScreen) { inclusive = true }
                }
            }
        }

        SplashScreen()
    }
}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    MovieAppTheme {
        SplashScreen()
    }
}
