package com.luis.antsimulator.data.network

data class Question(
    val choices: List<Choice>,
    val id: Int,
    val image: String?,
    val num: Int,
    val question_type: QuestionType,
    val text: String
)