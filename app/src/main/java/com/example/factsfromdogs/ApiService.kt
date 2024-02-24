package com.example.factsfromdogs

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/facts")
    fun getRandomDogFact(): Call<DogFactResponse>
}