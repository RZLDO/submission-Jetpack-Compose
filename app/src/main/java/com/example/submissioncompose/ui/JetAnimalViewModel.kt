package com.example.submissioncompose.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.submissioncompose.data.AnimalRepository
import com.example.submissioncompose.model.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class JetAnimalViewModel @Inject constructor(private val animalRepository: AnimalRepository): ViewModel(){
    private val _groupedAnimal = MutableStateFlow(
        animalRepository.getHeroes()
            .sortedBy { it.animalName }
            .groupBy { it.animalName[0] }
    )
    val groupedAnimal : StateFlow<Map<Char, List<Animal>>> get() = _groupedAnimal
    private val _query = mutableStateOf("")
    val query : State<String>get() = _query
    fun search(newQuery : String){
        _query.value = newQuery
        _groupedAnimal.value = animalRepository.searchAnimal(_query.value)
            .sortedBy { it.animalName }
            .groupBy { it.animalName[0] }
    }
}