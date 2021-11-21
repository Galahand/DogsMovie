package com.example.doglist.data.repository

import com.example.doglist.data.model.DogResponse
import com.example.doglist.data.network.DogService
import com.example.doglist.data.persistent.dao.DogDao
import com.example.doglist.data.persistent.entity.DogEntity
import com.example.doglist.domain.model.Dog
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DogRepositoryImplTest {

    @MockK
    lateinit var mockDogService: DogService

    @MockK
    lateinit var mockDogDao: DogDao

    lateinit var classUnderTest: DogRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        classUnderTest = DogRepositoryImpl(mockDogService, mockDogDao)
    }

    @Test
    fun `Returns dogs from database`() = runBlocking {
        // arrange
        val input = dogsEntity
        val dogFlow = flow { emit(input) }
        every { mockDogDao.getDogs() } returns dogFlow

        // act
        val result = classUnderTest.getDogsFromDB().first()

        // assert
        assertEquals(input.first().name, result.first().name)
        assertEquals(input.first().description, result.first().description)
        assertEquals(input.first().age, result.first().age)
        assertEquals(input.first().image, result.first().image)
    }

    @Test
    fun `Returns dogs from web`() = runBlocking {
        // arrange
        val input = dogsResponse
        coEvery { mockDogService.getDogs() } returns input

        // act
        val result = classUnderTest.getDogsFromWeb()

        // assert
        assertEquals(input.first().dogName, result.first().name)
        assertEquals(input.first().description, result.first().description)
        assertEquals(input.first().age, result.first().age)
        assertEquals(input.first().image, result.first().image)
    }

    @Test
    fun `Saves dogs on db`() = runBlocking {
        // arrange
        val input = dogsDomain
        val saved = slot<List<DogEntity>>()
        coJustRun { mockDogDao.insertDogs(capture(saved)) }

        // act
        classUnderTest.saveDogsOnDB(input)

        // assert
        assertEquals(input.first().name, saved.captured.first().name)
        assertEquals(input.first().description, saved.captured.first().description)
        assertEquals(input.first().age, saved.captured.first().age)
        assertEquals(input.first().image, saved.captured.first().image)
    }
}

val dogsEntity = listOf(
    DogEntity(
        name = "anyName",
        description = "anyDesc",
        age = 3,
        image = "image,"
    )
)

val dogsResponse = listOf(
    DogResponse(
        dogName = "anyName",
        description = "anyDesc",
        age = 3,
        image = "image,"
    )
)

val dogsDomain = listOf(
    Dog(
        name = "anyName",
        description = "anyDesc",
        age = 3,
        image = "image,"
    )
)