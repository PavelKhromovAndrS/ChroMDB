package com.example.chromdb.model.entities.rest_entities.top_rated

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDTO(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable