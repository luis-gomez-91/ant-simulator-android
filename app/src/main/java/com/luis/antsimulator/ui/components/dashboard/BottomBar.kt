package org.itb.sga.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyBottomBar(
    navController: NavHostController,
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Surface (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .height(96.dp)
    ){
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NavigationBarItem(
                selected = currentRoute == "home",
                onClick = {
                    navController.navigate("home")
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "home") },
                label = { Text("Inicio") }
            )

            NavigationBarItem(
                selected = currentRoute == "settings",
                onClick = {
                    navController.navigate("settings")
//                    Tema claro/oscuro
//                    Número de preguntas por prueba (por ejemplo: 10, 20, 30)
//                    Tiempo límite por prueba (activar/desactivar o elegir entre 15 min, 20 min, sin límite)
//                    Orden de preguntas: aleatorio / por tema / por dificultad
//                    Mostrar respuestas correctas al finalizar (sí/no)
//                    Activar modo estudio (ver la respuesta correcta inmediatamente)
                },
                icon = { Icon(Icons.Filled.Settings, contentDescription = "settings") },
                label = { Text("Ajustes") }
            )
        }
    }
}