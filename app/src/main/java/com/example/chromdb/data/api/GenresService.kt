package com.example.chromdb.data.api

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.repository.RepositoryImpl

import com.example.chromdb.ui.screens.FavoriteMovieFragment


class GenresService() : IntentService("MyGenresService") {
    private val repository =RepositoryImpl()

    override fun onHandleIntent(intent: Intent?) {
        val data = repository.getGenres()
        intent?.let {
            sendBack(data[1].name)
        }

        Toast.makeText(applicationContext, repository.getGenres()[1].name, Toast.LENGTH_SHORT).show()
    }
    private fun sendBack(result: String) {
        val broadcastIntent = Intent(FavoriteMovieFragment.GENRE_BROADCAST_INTENT_FILTER)
        broadcastIntent.putExtra(FavoriteMovieFragment.GENRE_ARG, result)
        sendBroadcast(broadcastIntent)
    }
}