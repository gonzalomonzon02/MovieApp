package com.gonzalomonzon02.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int?,
    val results: List<MovieDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) 