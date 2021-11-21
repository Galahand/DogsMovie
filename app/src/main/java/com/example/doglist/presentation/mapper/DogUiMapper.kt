package com.example.doglist.presentation.mapper

import com.example.doglist.domain.model.Dog
import com.example.doglist.presentation.model.DogUi

object DogUiMapper {
    fun toUi(dog: Dog) = DogUi(
        name = dog.name,
        description = dog.description,
        age = dog.age,
        image = dog.image,
    )

    fun toUi(dogs: List<Dog>) = dogs.map { toUi(it) }
}