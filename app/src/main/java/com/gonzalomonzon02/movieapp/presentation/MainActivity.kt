package com.gonzalomonzon02.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gonzalomonzon02.movieapp.presentation.navigation.MovieNavGraph
import com.gonzalomonzon02.movieapp.presentation.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            MovieAppTheme {
                Scaffold { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        MovieNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
