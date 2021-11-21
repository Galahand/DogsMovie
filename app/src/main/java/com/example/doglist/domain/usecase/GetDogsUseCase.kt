package com.example.doglist.domain.usecase

import com.example.doglist.domain.model.Dog
import com.example.doglist.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val repository: DogRepository,
) {
    operator fun invoke(): Flow<List<Dog>> = repository.getDogsFromDB()
        .onEach {
            if (it.isEmpty()) {
                val result = repository.getDogsFromWeb()
                if (result.isNotEmpty()) {
                    repository.saveDogsOnDB(result)
                } else {
                    throw Exception()
                }
            }
        }
}