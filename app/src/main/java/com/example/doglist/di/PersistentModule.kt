package com.example.doglist.di

import android.content.Context
import androidx.room.Room
import com.example.doglist.data.persistent.Database
import com.example.doglist.data.persistent.dao.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistentModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "dogs_database"
        ).build()
    }

    @Provides
    fun provideDogDao(database: Database): DogDao {
        return database.dogDao()
    }
}