package com.example.doglist.data.mapper

import com.example.doglist.data.model.DogResponse
import com.example.doglist.data.persistent.entity.DogEntity
import junit.framework.Assert.assertEquals
import org.junit.Test

class DogDataMapperTest {

    @Test
    fun `transforms entity to domain`() {
        // arrange
        val input = DogEntity(
            name = "Spots",
            description = "Is the brother of chief...",
            image = "imageUrl",
            age = 2,
        )

        // act
        val result = DogDataMapper.entityToDomain(input)

        // assert
        assertEquals(input.name, result.name)
        assertEquals(input.description, result.description)
        assertEquals(input.image, result.image)
        assertEquals(input.age, result.age)
    }

    @Test
    fun `transforms entity list to domain list`() {
        // arrange
        val input = listOf(
            DogEntity(
                name = "Spots",
                description = "Is the brother of chief...",
                image = "imageUrl",
                age = 1,
            ),
            DogEntity(
                name = "Spots 2",
                description = "Is the brother of chief...2",
                image = "imageUrl 2",
                age = 2,
            )
        )

        // act
        val result = DogDataMapper.entityToDomain(input)

        // assert
        for (i in result.indices) {
            assertEquals(input[i].name, result[i].name)
            assertEquals(input[i].description, result[i].description)
            assertEquals(input[i].image, result[i].image)
            assertEquals(input[i].age, result[i].age)
        }
    }

    @Test
    fun `transforms response to entity`() {
        // arrange
        val input = DogResponse(
            dogName = "Spots",
            description = "Is the brother of chief...",
            image = "imageUrl",
            age = 2,
        )

        // act
        val result = DogDataMapper.responseToEntity(input)

        // assert
        assertEquals(input.dogName, result.name)
        assertEquals(input.description, result.description)
        assertEquals(input.image, result.image)
        assertEquals(input.age, result.age)
    }

    @Test
    fun `transforms response list to entity list`() {
        // arrange
        val input = listOf(
            DogResponse(
                dogName = "Spots",
                description = "Is the brother of chief...",
                image = "imageUrl",
                age = 1,
            ),
            DogResponse(
                dogName = "Spots 2",
                description = "Is the brother of chief...2",
                image = "imageUrl 2",
                age = 2,
            )
        )

        // act
        val result = DogDataMapper.responseToEntity(input)

        // assert
        for (i in result.indices) {
            assertEquals(input[i].dogName, result[i].name)
            assertEquals(input[i].description, result[i].description)
            assertEquals(input[i].image, result[i].image)
            assertEquals(input[i].age, result[i].age)
        }
    }
}