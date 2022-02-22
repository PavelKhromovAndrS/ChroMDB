package com.example.chromdb.model

import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem

sealed class AppState {
    data class SuccessPopular(val popularData:List<MovieItem>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}