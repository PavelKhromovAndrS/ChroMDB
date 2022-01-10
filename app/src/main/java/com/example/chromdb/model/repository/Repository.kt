package com.example.chromdb.model.repository

import com.example.chromdb.model.entities.MovieItem

interface Repository {
    fun getMovieItemFromServer(): List<MovieItem>
    fun getMovieItemFromLocalStorage(): List<MovieItem>
}