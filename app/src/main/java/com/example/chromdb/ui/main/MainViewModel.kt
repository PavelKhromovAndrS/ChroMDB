package com.example.chromdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chromdb.model.entities.rest_entities.top_rated.MovieItem
import com.example.chromdb.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val popularLiveData: MutableLiveData<List<MovieItem>> = MutableLiveData()
    val topRatedLiveData: MutableLiveData<List<MovieItem>> = MutableLiveData()

    fun loadData() {

        viewModelScope.launch(Dispatchers.IO) {
            popularLiveData.postValue(repository.getPopularFromServer())
        }

        viewModelScope.launch(Dispatchers.IO) {
            topRatedLiveData.postValue(repository.getTopRatedFromServer())
        }
    }

    fun loadHistoryData(item: MovieItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveEntity(item)
        }
    }
}
