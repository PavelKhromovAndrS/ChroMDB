package com.example.chromdb.model.repository

import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem

interface Repository {
    suspend fun getGenreItemFromServer(): List<Genre>?

    suspend fun getPopularFromServer(): List<PopularMovieItem>?

    suspend fun getConfigFromServer(): Images?

    suspend fun getTopRatedFromServer(): List<TopRatedMovieItem>?
    suspend fun  getAllMovies(): List<TopRatedMovieItem>
   suspend fun saveEntity(movie: TopRatedMovieItem)
}