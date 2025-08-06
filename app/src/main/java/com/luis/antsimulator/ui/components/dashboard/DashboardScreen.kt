package org.itb.sga.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun DashBoardScreen(
    navController: NavHostController,
    content: @Composable () -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

        },
        content = {
            Scaffold(
//                topBar = { MyTopBar(title = title, drawerState = drawerState) },
                bottomBar = { MyBottomBar(navController = navController) }
            ) { innerPadding ->
                Surface(
                    Modifier
                        .padding(innerPadding)
                        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content()
                    }
                }
            }


       }
    )
}
