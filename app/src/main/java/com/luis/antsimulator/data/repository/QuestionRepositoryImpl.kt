package com.luis.antsimulator.data.repository

import com.luis.antsimulator.data.network.Question
import com.luis.antsimulator.domain.QuestionRepository
import com.luis.antsimulator.features.questions.QuestionService
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val api: QuestionService
) : QuestionRepository {
    override suspend fun getQuestions(id: Int): List<Question> = api.getQuestions(idLicencia = id)
}