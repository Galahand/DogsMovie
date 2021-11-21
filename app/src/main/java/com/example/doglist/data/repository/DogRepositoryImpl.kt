package com.example.doglist.data.repository

import com.example.doglist.data.mapper.DogDataMapper
import com.example.doglist.data.network.DogService
import com.example.doglist.data.persistent.dao.DogDao
import com.example.doglist.domain.model.Dog
import com.example.doglist.domain.repository.DogRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ViewModelScoped
class DogRepositoryImpl @Inject constructor(
    private val service: DogService,
    private val dogDao: DogDao,
) : DogRepository {

    override fun getDogsFromDB(): Flow<List<Dog>> {
        return dogDao
            .getDogs()
            .map(DogDataMapper::entityToDomain)
    }

    override suspend fun getDogsFromWeb(): List<Dog> {
        return DogDataMapper.responseToDomain(service.getDogs())
    }

    override suspend fun saveDogsOnDB(dogs: List<Dog>) {
        dogDao.insertDogs(DogDataMapper.domainToEntity(dogs))
    }
}


