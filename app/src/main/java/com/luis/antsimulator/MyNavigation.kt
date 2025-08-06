package com.luis.antsimulator

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luis.ant.screens.home.HomeScreen
import com.luis.ant.screens.home.HomeViewModel
import com.luis.antsimulator.features.SelectScreen
import com.luis.antsimulator.features.questions.QuestionScreen
import com.luis.antsimulator.features.settings.SettingsScreen
import com.luis.antsimulator.features.simulator.SimulatoreScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, homeViewModel)
        }

        composable("options") {
            SelectScreen(navController, homeViewModel)
        }

        composable(
            route = "questions/{licenceId}",
            arguments = listOf(navArgument("licenceId") { type = NavType.IntType })
        ) { backStackEntry ->
            val licenceId = backStackEntry.arguments?.getInt("licenceId")
            licenceId?.let {
                QuestionScreen( it, homeViewModel, navController)
            }
        }

        composable("simulator") {
            SimulatoreScreen(navController, homeViewModel)
        }

        composable("settings") {
            SettingsScreen(navController, homeViewModel)
        }
    }
}