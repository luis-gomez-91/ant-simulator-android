package com.luis.antsimulator.features.simulator

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.luis.ant.screens.home.HomeViewModel
import org.itb.sga.ui.components.dashboard.DashBoardScreen

@Composable
fun SimulatoreScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    DashBoardScreen(
        navController = navController,
        content = {
            Screen(
                homeViewModel
            )
        }
    )
}


@Composable
fun Screen(
    homeViewModel: HomeViewModel
) {
    val licence by homeViewModel.licenceSelect

    licence?.let {
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    // Configuración del WebView
                    settings.javaScriptEnabled = true  // Habilitar JavaScript si lo necesitas
                    webViewClient = WebViewClient()   // Manejar enlaces dentro del WebView
                    loadUrl("https://ant.com.ec/licencias/simulador/tipo-${it.name.lowercase()}")  // URL que deseas cargar
                }
            },
        )
    }

}