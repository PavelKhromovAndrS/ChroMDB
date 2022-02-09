package com.example.chromdb.model.repository

import androidx.room.TypeConverter
import com.example.chromdb.data.api.RetrofitInstance
import com.example.chromdb.model.database.Database
import com.example.chromdb.model.database.HistoryEntity
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.google.gson.Gson

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

    override suspend fun getAllMovies(): List<TopRatedMovieItem> {
        return convertHistoryEntityToMovie(Database.db.historyDao().all())
    }

    override suspend fun saveEntity(movie: TopRatedMovieItem) {
        Database.db.historyDao().insert(convertMovieToEntity(movie))
    }

    private fun convertMovieToEntity(movie: TopRatedMovieItem): HistoryEntity {
        return HistoryEntity(
            movie.adult,
            movie.backdrop_path,
            movie.genre_ids,
            movie.id,
            movie.original_language,
            movie.original_title,
            movie.overview,
            movie.popularity,
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.video,
            movie.vote_average,
            movie.vote_count
        )
    }

    private fun convertHistoryEntityToMovie(entityList: List<HistoryEntity>): List<TopRatedMovieItem> {
        return entityList.map {
            TopRatedMovieItem(
                it.adult,
                it.backdrop_path,
                it.genre_ids,
                it.id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.release_date,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count
            )
        }
    }
}

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun genreIdToJson(genreId: List<Int>): String = Gson().toJson(genreId)

        @TypeConverter
        @JvmStatic
        fun jsonToGenreId(genreId: String) =
            Gson().fromJson(genreId, Array<Int>::class.java).toList()
    }
}