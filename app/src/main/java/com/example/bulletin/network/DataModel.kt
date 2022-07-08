package com.example.bulletin.network

data class DataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)