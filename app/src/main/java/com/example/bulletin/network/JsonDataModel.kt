package com.example.bulletin.network

data class JsonDataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)