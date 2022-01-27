package com.example.chromdb.model.entities.rest_entities.config

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfigDTO(
    val change_keys: List<String>,
    val images: Images
) : Parcelable