package com.example.chromdb

import com.example.chromdb.model.repository.Repository
import com.example.chromdb.model.repository.RepositoryImpl
import com.example.chromdb.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  appModule = module{
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
}