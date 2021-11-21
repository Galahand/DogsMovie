package com.example.doglist.di

import com.example.doglist.data.repository.DogRepositoryImpl
import com.example.doglist.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsDogRepository(impl: DogRepositoryImpl): DogRepository
}