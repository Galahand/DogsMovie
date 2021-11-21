package com.example.doglist.data.persistent.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogEntity(
    @NonNull val name: String,
    @NonNull val description: String,
    @NonNull val image: String,
    @NonNull val age: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)