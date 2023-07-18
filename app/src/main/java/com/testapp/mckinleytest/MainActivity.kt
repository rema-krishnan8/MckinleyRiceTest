package com.testapp.mckinleytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.testapp.mckinleytest.ui.theme.MckinleyTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MckinleyTestTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navHostEngne = rememberNavHostEngine()
                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route

                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navHostEngne
                    )
                }
            }
        }
    }
}
