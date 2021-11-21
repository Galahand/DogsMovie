package com.example.doglist.data.mapper

import com.example.doglist.data.model.DogResponse
import com.example.doglist.data.persistent.entity.DogEntity
import com.example.doglist.domain.model.Dog

object DogDataMapper {
    fun entityToDomain(dog: DogEntity) = Dog(
        name = dog.name,
        description = dog.description,
        age = dog.age,
        image = dog.image,
    )

    fun entityToDomain(dogs: List<DogEntity>) = dogs.map { entityToDomain(it) }

    fun responseToEntity(dog: DogResponse) = DogEntity(
        name = dog.dogName,
        description = dog.description,
        image = dog.image,
        age = dog.age,
    )

    fun responseToEntity(dogs: List<DogResponse>) = dogs.map { responseToEntity(it) }

    fun responseToDomain(dog: DogResponse) = Dog(
        name = dog.dogName,
        description = dog.description,
        age = dog.age,
        image = dog.image,
    )

    fun responseToDomain(dogs: List<DogResponse>) = dogs.map { responseToDomain(it) }

    fun domainToEntity(dog: Dog) = DogEntity(
        name = dog.name,
        description = dog.description,
        image = dog.image,
        age = dog.age,
    )

    fun domainToEntity(dogs: List<Dog>) = dogs.map { domainToEntity(it) }
}