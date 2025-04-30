package com.luis.ant.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val data = homeViewModel.licences.value

    LazyColumn {
        items(data) { type ->
            Text("Tipo: ${type.type_name}")
        }
    }
}