package com.luis.antsimulator.domain

import com.luis.antsimulator.data.network.Question

interface QuestionRepository {
    suspend fun getQuestions(idLicencia: Int): List<Question>
}