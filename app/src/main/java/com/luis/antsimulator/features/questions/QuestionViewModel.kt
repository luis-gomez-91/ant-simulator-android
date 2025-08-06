package com.luis.antsimulator.features.questions

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luis.antsimulator.data.network.Question
import com.luis.antsimulator.domain.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    private val _questions = mutableStateOf<List<Question>>(emptyList())
    val questions: State<List<Question>> = _questions

    private val _questionsSelect = mutableStateOf<Question?>(null)
    val questionsSelect: State<Question?> = _questionsSelect

    fun setQuestionSelect(question: Question?) {
        _questionsSelect.value = question
    }

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    fun getQuestions(id: Int){
        try {
            _isLoading.value = true
            viewModelScope.launch {
                try {
                    val result = repository.getQuestions(id)
                    _questions.value = result
                    Log.d("prueba", "Preguntas obtenidas: ${result.size}")
                } catch (e: Exception) {
                    Log.e("prueba", "Error al obtener preguntas: ${e.localizedMessage}", e)
                }
            }
        } catch (e: Exception) {
            Log.e("prueba", "Error inicializando el ViewModel: ${e.localizedMessage}", e)
        } finally {
            _isLoading.value = false
        }
    }
}