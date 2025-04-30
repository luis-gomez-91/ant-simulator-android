package com.luis.ant.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luis.ant.data.network.LicenceType
import com.luis.ant.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val licences = mutableStateOf<List<LicenceType>>(emptyList())

    init {
        viewModelScope.launch {
            licences.value = repository.getLicences()
        }
    }
}