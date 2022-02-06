package com.example.chromdb.model.repository

import com.example.chromdb.data.api.RetrofitInstance
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem

class RepositoryImpl : Repository {

    override suspend fun getGenreItemFromServer(): List<Genre>? {
        return RetrofitInstance.api.getGenres().body()?.genres
    }

    override suspend fun getPopularFromServer(): List<PopularMovieItem>? {
        return RetrofitInstance.api.getPopular().body()?.results
    }

    override suspend fun getConfigFromServer(): Images? {
        return RetrofitInstance.api.getConfig().body()?.images
    }

    override suspend fun getTopRatedFromServer(): List<TopRatedMovieItem>? {
        return RetrofitInstance.api.getTopRated().body()?.results
    }


}