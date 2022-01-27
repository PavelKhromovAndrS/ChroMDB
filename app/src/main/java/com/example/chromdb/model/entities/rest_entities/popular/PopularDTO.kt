package com.example.chromdb.model.entities.rest_entities.popular

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularDTO(
    val page: Int,
    val results: List<PopularMovieItem>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable