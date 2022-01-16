package com.example.chromdb.model

import com.example.chromdb.model.entities.MovieItem

sealed class AppState {
    data class Success(val movieData: List<MovieItem>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}