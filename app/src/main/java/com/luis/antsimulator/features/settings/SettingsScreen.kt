package com.luis.antsimulator.features.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.luis.ant.screens.home.HomeViewModel
import org.itb.sga.ui.components.dashboard.DashBoardScreen

@Composable
fun SettingsScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    DashBoardScreen(
        navController = navHostController,
        content = {
            Text("HOLA")
        }
    )
}