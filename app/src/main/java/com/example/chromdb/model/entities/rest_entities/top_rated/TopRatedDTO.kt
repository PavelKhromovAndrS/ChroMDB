package com.example.chromdb.model.entities.rest_entities.top_rated

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopRatedDTO(
    val page: Int,
    val results: List<TopRatedMovieItem>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable