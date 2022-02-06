package com.example.chromdb.model

import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem

sealed class AppState {
    data class SuccessPopular(val popularData:List<PopularMovieItem>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}