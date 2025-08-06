package com.luis.antsimulator.features.questions

import com.luis.antsimulator.data.network.Question
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionService {
    @GET("questions_only/")
    suspend fun getQuestions(
        @Query("id_licencia") idLicencia: Int
    ): List<Question>
}