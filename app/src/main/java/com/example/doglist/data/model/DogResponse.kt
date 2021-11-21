package com.example.doglist.data.model

import com.squareup.moshi.Json

data class DogResponse(
    @Json(name = "dogName") val dogName: String,
    @Json(name = "description") val description: String,
    @Json(name = "age") val age: Int,
    @Json(name = "image") val image: String,
)