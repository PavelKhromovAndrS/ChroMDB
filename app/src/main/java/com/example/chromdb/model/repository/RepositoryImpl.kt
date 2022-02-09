package com.example.chromdb.model.repository

import androidx.room.TypeConverter
import com.example.chromdb.data.api.RetrofitInstance
import com.example.chromdb.model.database.Converters
import com.example.chromdb.model.database.Database
import com.example.chromdb.model.database.HistoryEntity
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.google.gson.Gson

class RepositoryImpl : Repository {

    val converters: Converters = Converters()

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

    override suspend fun getAllMovies(): List<TopRatedMovieItem> {
        return converters.convertHistoryEntityToMovie(Database.db.historyDao().all())
    }

    override suspend fun saveEntity(movie: TopRatedMovieItem) {
        Database.db.historyDao().insert(converters.convertMovieToEntity(movie))
    }


}
