package com.example.doglist.data.network

import com.example.doglist.data.model.DogResponse
import retrofit2.http.GET

interface DogService {
    @GET("/api/880188946124021760")
    suspend fun getDogs(): List<DogResponse>
}