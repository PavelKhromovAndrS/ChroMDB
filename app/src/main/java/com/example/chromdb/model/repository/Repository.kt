package com.example.chromdb.model.repository

import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem

interface Repository {

    suspend fun getPopularFromServer(): List<MovieItem>?

    suspend fun getConfigFromServer(): Images?

    suspend fun getTopRatedFromServer(): List<MovieItem>?

    suspend fun getAllMovies(): List<MovieItem>

    suspend fun saveEntity(movie: MovieItem)
}