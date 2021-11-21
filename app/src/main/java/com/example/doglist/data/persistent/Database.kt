package com.example.doglist.data.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doglist.data.persistent.dao.DogDao
import com.example.doglist.data.persistent.entity.DogEntity

@Database(entities = [DogEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun dogDao(): DogDao
}