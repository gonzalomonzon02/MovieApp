package com.gonzalomonzon02.movieapp.domain.usecase

import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.utils.TestMovieSamples
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.whenever

class GetMovieDetailUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getMovieDetailUseCase = GetMovieDetailUseCase(movieRepository)
    }

    @Test
    fun `get movie detail returns correct value`() = runTest {
        val movieIdExpected = 3
        val movieExpected = TestMovieSamples.movie.copy(id = movieIdExpected)

        whenever(
            movieRepository.getMovieDetail(movieId = anyOrNull())
        ).thenReturn(
            movieExpected
        )

        val result = getMovieDetailUseCase(movieId = movieIdExpected)

        assertNotNull(result)
        assertEquals(movieIdExpected, result.id)
    }

    @Test
    fun `invoke returns null when repository returns null`() = runTest {
        val movieId = 123

        whenever(
            movieRepository.getMovieDetail(movieId)
        ).thenReturn(
            null
        )

        val result = getMovieDetailUseCase.invoke(movieId)

        assertNull(result)
    }
}