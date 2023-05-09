package com.example.submissioncompose.data

import com.example.submissioncompose.model.Animal
import com.example.submissioncompose.model.AnimalData
import javax.inject.Inject

class AnimalRepository @Inject constructor(){
    fun getHeroes():List<Animal>{
        return AnimalData.animal
    }

    fun searchAnimal(query:String):List<Animal>{
        return AnimalData.animal.filter {
            it.animalName.contains(query, ignoreCase = true)
        }
    }
}