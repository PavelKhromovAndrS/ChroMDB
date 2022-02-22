package com.example.chromdb.model.repository

import com.example.chromdb.data.api.RetrofitInstance
import com.example.chromdb.model.database.Converters
import com.example.chromdb.model.database.Database
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem

class RepositoryImpl : Repository {

    val converters: Converters = Converters()

    override suspend fun getPopularFromServer(): List<MovieItem>? {
        return RetrofitInstance.api.getPopular().body()?.results
    }

    override suspend fun getConfigFromServer(): Images? {
        return RetrofitInstance.api.getConfig().body()?.images
    }

    override suspend fun getTopRatedFromServer(): List<MovieItem>? {
        return RetrofitInstance.api.getTopRated().body()?.results
    }

    override suspend fun getAllMovies(): List<MovieItem> {
        return converters.convertHistoryEntityToMovie(Database.db.historyDao().all())
    }

    override suspend fun saveEntity(movie: MovieItem) {
        Database.db.historyDao().insert(converters.convertMovieToEntity(movie))
    }


}
