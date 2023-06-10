package com.example.pokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("pokemon/{id}")
    fun getNumbers(@Path("id") id: String?): Call<DetailModel>
}