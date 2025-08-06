package com.luis.ant.di

import com.luis.ant.data.repository.HomeRepositoryImpl
import com.luis.ant.domain.HomeRepository
import com.luis.ant.screens.home.HomeService
import com.luis.antsimulator.domain.QuestionRepository
import com.luis.antsimulator.features.questions.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://antsimulator.onrender.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeService): HomeRepository =
        HomeRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideQuestionsService(retrofit: Retrofit): QuestionService =
        retrofit.create(QuestionService::class.java)

    @Provides
    @Singleton
    fun provideQuestionRepository(api: QuestionService): QuestionRepository =
        com.luis.antsimulator.data.repository.QuestionRepositoryImpl(api)
}