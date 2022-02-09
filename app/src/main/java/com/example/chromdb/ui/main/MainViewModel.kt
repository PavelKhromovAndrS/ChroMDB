package com.example.chromdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chromdb.model.AppState
import com.example.chromdb.model.entities.rest_entities.config.ConfigDTO
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.Genre
import com.example.chromdb.model.entities.rest_entities.genres.GenreDTO
import com.example.chromdb.model.entities.rest_entities.popular.PopularDTO
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedDTO
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.example.chromdb.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val popularLiveData: MutableLiveData<List<PopularMovieItem>> = MutableLiveData()
    val topRatedLiveData: MutableLiveData<List<TopRatedMovieItem>> = MutableLiveData()

    fun loadData() {

        viewModelScope.launch(Dispatchers.IO) {
            popularLiveData.postValue(repository.getPopularFromServer())
        }

        viewModelScope.launch(Dispatchers.IO) {
            topRatedLiveData.postValue(repository.getTopRatedFromServer())
        }
    }

    fun loadHistoryData(item: TopRatedMovieItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveEntity(item)
        }
    }
}
