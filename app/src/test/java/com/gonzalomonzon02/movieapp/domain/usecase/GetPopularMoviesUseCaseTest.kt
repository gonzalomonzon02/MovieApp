package com.gonzalomonzon02.movieapp.domain.usecase

import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.utils.TestMovieSamples
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever

class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `get movies from remote`() = runTest {
        val titleExpected = "Matrix"

        whenever(
            movieRepository.getPopularMovies(
                page = anyOrNull(),
                sortBy = anyOrNull(),
                genres = anyOrNull(),
                query = anyOrNull(),
                source = eq(Source.Remote)
            )
        ).thenReturn(
            listOf(TestMovieSamples.movie.copy(title = titleExpected))
        )

        val result = getPopularMoviesUseCase(source = Source.Remote)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(titleExpected, result.first().title)
    }

    @Test
    fun `get movies from local`() = runTest {
        val titleExpected = "Avatar"
        whenever(
            movieRepository.getPopularMovies(
                page = anyOrNull(),
                sortBy = anyOrNull(),
                genres = anyOrNull(),
                query = anyOrNull(),
                source = eq(Source.Local)
            )
        ).thenReturn(
            listOf(TestMovieSamples.movie.copy(title = titleExpected))
        )

        val result = getPopularMoviesUseCase(source = Source.Local)

        assertTrue(result.isNotEmpty())
        assertEquals(titleExpected, result.first().title)
    }

    @Test
    fun `empty movies list returns empty`() = runTest {
        whenever(
            movieRepository.getPopularMovies(
                page = anyOrNull(),
                sortBy = anyOrNull(),
                genres = anyOrNull(),
                query = anyOrNull(),
                source = anyOrNull()
            )
        ).thenReturn(emptyList())

        val result = getPopularMoviesUseCase(source = Source.Remote)

        assertTrue(result.isEmpty())
    }
}
