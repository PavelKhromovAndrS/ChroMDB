package com.example.chromdb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chromdb.model.AppState
import com.example.chromdb.model.entities.rest_entities.config.ConfigDTO
import com.example.chromdb.model.entities.rest_entities.config.Images
import com.example.chromdb.model.entities.rest_entities.genres.GenreDTO
import com.example.chromdb.model.entities.rest_entities.popular.PopularDTO
import com.example.chromdb.model.entities.rest_entities.popular.PopularMovieItem
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedDTO
import com.example.chromdb.model.entities.rest_entities.top_rated.TopRatedMovieItem
import com.example.chromdb.model.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val popularLiveData: MutableLiveData<List<PopularMovieItem>> = MutableLiveData()
    val configLiveData: MutableLiveData<Images> = MutableLiveData()
    val topRatedLiveData: MutableLiveData<List<TopRatedMovieItem>> = MutableLiveData()

    fun loadData() {
        viewModelScope.async {
            popularLiveData.value = repository.getPopularFromServer()
        }
        viewModelScope.async {
            configLiveData.value = repository.getConfigFromServer()
        }
        viewModelScope.async {
            topRatedLiveData.value = repository.getTopRatedFromServer()
        }
    }
}
