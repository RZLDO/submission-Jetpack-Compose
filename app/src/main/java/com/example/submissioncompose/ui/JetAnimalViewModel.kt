package com.example.submissioncompose.ui

import androidx.lifecycle.ViewModel
import com.example.submissioncompose.data.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class JetAnimalViewModel (private val animalRepository: AnimalRepository): ViewModel(){

}