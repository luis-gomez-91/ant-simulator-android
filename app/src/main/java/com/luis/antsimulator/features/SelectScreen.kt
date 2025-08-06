package com.luis.antsimulator.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luis.ant.screens.home.HomeViewModel
import org.itb.sga.ui.components.MyFilledTonalButton
import org.itb.sga.ui.components.dashboard.DashBoardScreen

@Composable
fun SelectScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    DashBoardScreen(
        navController = navController,
        content = {
            Screen(
                navController,
                homeViewModel
            )
        }
    )
}

@Composable
fun Screen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val licence by homeViewModel.licenceSelect

    licence?.let {
        Column (
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Licencia tipo ${it.name}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Licencia tipo ${it.description}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(32.dp))

            MyFilledTonalButton(
                text = "Simulador",
                enabled = true,
                icon = Icons.AutoMirrored.Filled.Assignment,
                iconSize = 36.dp,
                textColor = MaterialTheme.colorScheme.secondary,
                buttonColor = MaterialTheme.colorScheme.secondaryContainer,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                onClickAction = {
                    navController.navigate("simulator")
                }
            )

            Spacer(Modifier.height(16.dp))

            MyFilledTonalButton(
                text = "Banco de preguntas",
                enabled = true,
                icon = Icons.Filled.Folder,
                iconSize = 36.dp,
                textColor = MaterialTheme.colorScheme.tertiary,
                buttonColor = MaterialTheme.colorScheme.tertiaryContainer,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                onClickAction = {
                    navController.navigate("questions/${it.id}")
                }
            )

        }
    }
}