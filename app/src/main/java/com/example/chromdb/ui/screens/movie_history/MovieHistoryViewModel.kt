package com.example.chromdb.ui.screens.movie_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem
import com.example.chromdb.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieHistoryViewModel(private val repository: Repository) : ViewModel() {
    val historyLiveData: MutableLiveData<List<MovieItem>> = MutableLiveData()

    fun getAllHistory() {
        viewModelScope.launch(Dispatchers.IO){
            historyLiveData.postValue(repository.getAllMovies())
        }
    }
}