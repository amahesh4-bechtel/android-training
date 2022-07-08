package com.example.bulletin.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("top-headlines?country=in&apiKey=e58c81887286472ca6761c123b83fe28")
    fun getData(): Call<JsonDataModel>
}