package com.gonzalomonzon02.movieapp.data.repository

import com.gonzalomonzon02.movieapp.data.local.MovieDao
import com.gonzalomonzon02.movieapp.data.local.MovieEntity
import com.gonzalomonzon02.movieapp.data.model.MovieListResponse
import com.gonzalomonzon02.movieapp.data.remote.MovieService
import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.utils.TestMovieSamples
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieRepositoryImplTest {

    @Mock
    private lateinit var movieService: MovieService

    @Mock
    private lateinit var movieDao: MovieDao

    private lateinit var repository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MovieRepositoryImpl(movieService, movieDao)
    }

    @Test
    fun `get movies from remote saves to local`() = runTest {
        val titleExpected = "Avatar"

        whenever(
            movieService.getPopularMovies(any())
        ).thenReturn(
            MovieListResponse(
                page = 1,
                results = listOf(TestMovieSamples.movieDto.copy(title = titleExpected)),
                totalPages = 1,
                totalResults = 1
            )
        )

        val result = repository.getPopularMovies(source = Source.Remote)

        assertEquals(1, result.size)
        verify(movieDao).clearMovies()

        val captor = argumentCaptor<List<MovieEntity>>()
        verify(movieDao).insertMovies(captor.capture())
        assertEquals(titleExpected, captor.firstValue.first().title)
    }

    @Test
    fun `get movies from local returns local data`() = runTest {
        val titleExpected = "Titanic"

        whenever(
            movieDao.getAllMovies()
        ).thenReturn(
            listOf(
                TestMovieSamples.movieEntity.copy(title = titleExpected)
            )
        )

        val result = repository.getPopularMovies(source = Source.Local)

        assertEquals(1, result.size)
        assertEquals(titleExpected, result.first().title)
    }
}