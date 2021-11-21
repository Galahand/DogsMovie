package com.example.doglist.domain.repository

import com.example.doglist.domain.model.Dog
import com.example.doglist.domain.model.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DogRepository {
    fun getDogsFromDB(): Flow<List<Dog>>
    suspend fun getDogsFromWeb(): List<Dog>
    suspend fun saveDogsOnDB(dogs: List<Dog>)
}
