package com.example.chromdb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chromdb.model.AppState
import com.example.chromdb.model.entities.MovieItem
import com.example.chromdb.model.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getMovie() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveData.postValue(AppState.Success(repository.getMovieItemFromServer()))
        }.start()
    }
}