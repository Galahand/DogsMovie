package com.example.doglist.domain.usecase

import com.example.doglist.domain.model.Dog
import com.example.doglist.domain.repository.DogRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetDogUseCaseTest {

    @MockK
    lateinit var mockDogRepository: DogRepository

    private lateinit var classUnderTest: GetDogsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        classUnderTest = GetDogsUseCase(mockDogRepository)
    }

    @Test
    fun `Returns dog list from database`() = runBlocking {
        // arrange
        val input = listOf(dog.copy())
        val dogFlow = flow { emit(input) }
        every { mockDogRepository.getDogsFromDB() } returns dogFlow

        // act
        val result = classUnderTest().first()

        // assert
        assertEquals(input.first(), result.first())
    }

    @Test
    fun `Fetches dogs and saves in db if db is Empty`() = runBlocking {
        // arrange
        val dbDogs = emptyList<Dog>()
        val responseDogs = listOf(dog.copy())
        val dbDogsFlow = flow { emit(dbDogs) }
        val savedDogs = slot<List<Dog>>()

        every { mockDogRepository.getDogsFromDB() } returns dbDogsFlow
        coEvery { mockDogRepository.getDogsFromWeb() } returns responseDogs
        coJustRun { mockDogRepository.saveDogsOnDB(capture(savedDogs)) }

        // act
        val result = classUnderTest.invoke().first()

        // assert
        assertTrue(result.isEmpty())
        assertEquals(responseDogs.first(), savedDogs.captured.first())
    }

    @Test
    fun `Throws Exception if result is empty`() = runBlocking {
        // arrange
        val dbDogs = emptyList<Dog>()
        val responseDogs = emptyList<Dog>()
        val dbDogsFlow = flow { emit(dbDogs) }

        every { mockDogRepository.getDogsFromDB() } returns dbDogsFlow
        coEvery { mockDogRepository.getDogsFromWeb() } returns responseDogs

        // act
        val exception = try {
            classUnderTest.invoke().first()
            null
        } catch (e: Exception) {
            e
        }

        // assert
        assertNotNull(exception)
    }
}

val dog = Dog(
    name = "anyName",
    description = "anyDesc",
    age = 3,
    image = "image,"
)