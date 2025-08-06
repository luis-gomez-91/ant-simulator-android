package com.luis.ant.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luis.ant.data.network.Licence
import com.luis.ant.data.network.LicenceType
import com.luis.ant.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _licences = mutableStateOf<List<LicenceType>>(emptyList())
    val licences: State<List<LicenceType>> = _licences

    private val _licenceSelect = mutableStateOf<Licence?>(null)
    val licenceSelect: State<Licence?> = _licenceSelect

    fun setLicenteSelect(licence: Licence?) {
        _licenceSelect.value = licence
    }

    init {
        try {
            viewModelScope.launch {
                try {
                    val response = repository.getLicences()
                    _licences.value = response  // Asigna el valor a `licences`

                    Log.i("prueba", "Licencias obtenidas: ${_licences.value}")
                } catch (e: Exception) {
                    Log.e("prueba", "Error obteniendo licencias: ${e.localizedMessage}", e)
                }
            }
        } catch (e: Exception) {
            Log.e("prueba", "Error inicializando el ViewModel: ${e.localizedMessage}", e)
        }
    }

}