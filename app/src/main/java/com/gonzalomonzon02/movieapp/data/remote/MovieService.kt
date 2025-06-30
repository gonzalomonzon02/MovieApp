package com.gonzalomonzon02.movieapp.data.remote

import com.gonzalomonzon02.movieapp.data.model.MovieDto
import com.gonzalomonzon02.movieapp.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    )

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("year") year: Int? = null
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "credits,videos,images"
    ): MovieDto

    @GET("movie/{movie_id}/similar")
    suspend fun getRelatedMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("with_genres") withGenres: String? = null,
        @Query("with_keywords") withKeywords: String? = null,
        @Query("year") year: Int? = null,
        @Query("vote_average_gte") voteAverageGte: Double? = null
    ): MovieListResponse
}