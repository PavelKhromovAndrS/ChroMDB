package com.example.chromdb.model.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.chromdb.model.repository.Converters

@Entity
data class HistoryEntity(
    val adult: Boolean,
    val backdrop_path: String,
     val genre_ids: List<Int>,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
