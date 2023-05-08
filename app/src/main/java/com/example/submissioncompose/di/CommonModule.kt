package com.example.submissioncompose.di

import com.example.submissioncompose.data.AnimalRepository
import com.example.submissioncompose.ui.JetAnimalViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule{
    @Provides
    @Singleton
    fun provideAnimalViewModel(animalRepository: AnimalRepository) = JetAnimalViewModel(animalRepository)
}