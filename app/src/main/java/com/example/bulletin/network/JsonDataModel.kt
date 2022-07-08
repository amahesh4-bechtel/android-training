package com.example.bulletin.network

data class JsonDataModel(
//    val body: String,
//    val id: Int,
//    val title: String,
//    val userId: Int
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)