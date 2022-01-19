package com.example.chromdb.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val id: Int,
    val title: String,
    val image:String,
    val year: Int,
    val rating: Int,
    val description: String,
    val favorite: Boolean

    ) : Parcelable
