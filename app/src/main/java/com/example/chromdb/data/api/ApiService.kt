package com.example.chromdb.data.api


import com.example.chromdb.model.entities.rest_entities.config.ConfigDTO
import com.example.chromdb.model.entities.rest_entities.genres.GenreDTO
import com.example.chromdb.model.entities.rest_entities.popular.PopularDTO
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/genre/movie/list?api_key=db6a136ffcf1fb5ecafb07a032c15b9b&language=en-US")
    suspend fun getGenres(): Response<GenreDTO>

    @GET("3/movie/popular?api_key=db6a136ffcf1fb5ecafb07a032c15b9b&language=ru&page=1")
    suspend fun getPopular(): Response<PopularDTO>

    @GET("3/configuration?api_key=db6a136ffcf1fb5ecafb07a032c15b9b")
    suspend fun getConfig(): Response<ConfigDTO>

    @GET("3/movie/top_rated?api_key=db6a136ffcf1fb5ecafb07a032c15b9b&language=ru&page=1")
    suspend fun getTopRated(): Response<TopRatedDTO>
}