package com.example.memesapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("gimme")
    fun getMeme(): Call<MemeModel>

}