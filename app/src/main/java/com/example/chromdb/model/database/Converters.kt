package com.example.chromdb.model.database

import androidx.room.TypeConverter
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.google.gson.Gson

class Converters {
     fun convertMovieToEntity(movie: TopRatedMovieItem): HistoryEntity {
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

     fun convertHistoryEntityToMovie(entityList: List<HistoryEntity>): List<TopRatedMovieItem> {
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