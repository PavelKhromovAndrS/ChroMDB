package com.example.chromdb.model.repository

import com.example.chromdb.model.entities.MovieItem

class RepositoryImpl : Repository {
    val movieListFromServer: List<MovieItem> = listOf(
        MovieItem(1, "Matrix", 1, 2000),
        MovieItem(2, "Matrix2", 2, 2001)
    )
    val movieListFromLocalStorage: List<MovieItem> = listOf(
        MovieItem(1, "Matrix3", 3, 2003),
        MovieItem(2, "Matrix4", 4, 2004)
    )

    override fun getMovieItemFromServer() = movieListFromServer
    override fun getMovieItemFromLocalStorage() = movieListFromLocalStorage
}